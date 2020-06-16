package com.prudhvir3ddy.todo_app_gettingthingsdone.view.detail

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.text.TextUtils
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.prudhvir3ddy.todo_app_gettingthingsdone.BuildConfig
import com.prudhvir3ddy.todo_app_gettingthingsdone.R
import com.prudhvir3ddy.todo_app_gettingthingsdone.ToDoApp
import com.prudhvir3ddy.todo_app_gettingthingsdone.databinding.ActivityDetailBinding
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db.ToDo
import com.prudhvir3ddy.todo_app_gettingthingsdone.utils.IntentConstants
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.main.TasksActivity
import com.prudhvir3ddy.todo_app_gettingthingsdone.viewmodels.ViewModelFactory
import kotlinx.android.synthetic.main.dialog_image_source_selector.view.camera_tv
import kotlinx.android.synthetic.main.dialog_image_source_selector.view.gallery_tv
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

  @Inject
  lateinit var viewModelFactory: ViewModelFactory

  private lateinit var viewModel: DetailViewModel

  companion object {
    const val GALLERY_PICK_RC = 2
    const val CAMERA_CAPTURE_RC = 1
  }

  private lateinit var binding: ActivityDetailBinding
  private lateinit var currentPhotoPath: String

  var todo: ToDo? = ToDo()

  override fun onCreate(savedInstanceState: Bundle?) {
    (application as ToDoApp).appComponent.inject(this)
    super.onCreate(savedInstanceState)
    binding = ActivityDetailBinding.inflate(layoutInflater)
    setContentView(binding.root)

    viewModel = ViewModelProvider(this, viewModelFactory)[DetailViewModel::class.java]
    setToolbar()
    val intent = intent

    setViews(intent)
    binding.imagePathIv.setOnClickListener {
      setupDialog()
    }
  }

  private fun setViews(intent: Intent?) {
    todo = intent?.getParcelableExtra(IntentConstants.TODO)
    binding.apply {
      titleEt.setText(todo?.title, TextView.BufferType.EDITABLE)
      descriptionEt.setText(todo?.description, TextView.BufferType.EDITABLE)
    }
    val image = todo?.imagePath
    if (image != null && !TextUtils.isEmpty(image)) {
      Glide.with(this).load(image).into(binding.imagePathIv)
    }
  }

  private fun setToolbar() {
    supportActionBar?.title = "Get it Done"
  }

  private fun setupDialog() {
    val view = LayoutInflater.from(this).inflate(R.layout.dialog_image_source_selector, null)
    val cameraTv = view.camera_tv
    val galleryTv = view.gallery_tv

    val dialog = AlertDialog.Builder(this)
      .setCancelable(true)
      .setTitle("Choose an action")
      .setView(view)
      .create()
    dialog.show()

    cameraTv.setOnClickListener {
      createImageFile()
      takePicture()
      dialog.hide()

    }

    galleryTv.setOnClickListener {
      val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
      startActivityForResult(intent, GALLERY_PICK_RC)
      dialog.hide()
    }
  }

  private fun takePicture() {
    Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
      // Ensure that there's a camera activity to handle the intent
      takePictureIntent.resolveActivity(packageManager)?.also {
        // Create the File where the photo should go
        val photoFile: File? = try {
          createImageFile()
        } catch (ex: IOException) {
          // Error occurred while creating the File
          null
        }
        // Continue only if the File was successfully created
        photoFile?.also {
          val photoURI: Uri = FileProvider.getUriForFile(
            this,
            "${BuildConfig.APPLICATION_ID}.fileprovider",
            it
          )
          takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
          startActivityForResult(takePictureIntent, CAMERA_CAPTURE_RC)
        }
      }
    }
  }

  @Throws(IOException::class)
  private fun createImageFile(): File? {
    // Create an image file name
    val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(Date())
    val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    return File.createTempFile(
      "JPEG_${timeStamp}_", /* prefix */
      ".jpg", /* suffix */
      storageDir /* directory */
    ).apply {
      // Save a file: path for use with ACTION_VIEW intents
      currentPhotoPath = absolutePath
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)

    if (resultCode == Activity.RESULT_OK) {
      when (requestCode) {
        GALLERY_PICK_RC -> {
          val selectedImage = data?.data
          currentPhotoPath = selectedImage.toString()
          Glide.with(this).load(selectedImage).into(binding.imagePathIv)
        }
        CAMERA_CAPTURE_RC -> {
          Glide.with(this).load(currentPhotoPath).into(binding.imagePathIv)
        }
      }
    }
  }

  override fun onBackPressed() {
    val picPath = if (::currentPhotoPath.isInitialized) {
      currentPhotoPath
    } else {
      ""
    }
    val todo =
      ToDo(
        id = todo?.id,
        title = binding.titleEt.text.toString(),
        description = binding.descriptionEt.text.toString(),
        imagePath = picPath,
        isCompleted = todo?.isCompleted ?: false
      )
    val intent = Intent(this, TasksActivity::class.java)
    intent.putExtra(IntentConstants.TODO, todo)
    setResult(Activity.RESULT_OK, intent)
    finish()
  }

  override fun onSupportNavigateUp(): Boolean {
    onBackPressed()
    return true
  }

}
