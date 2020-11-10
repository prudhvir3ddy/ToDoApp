echo "$GOOGLE_SERVICES" >> ./app/google-services.json
echo "$KEY_STORE" | base64 --decode >> ./app/keystore/release.keystore