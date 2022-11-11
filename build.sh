#!/bin/bash
# add environment path
# export PATH="/opt/android-sdk/cmdline-tools/latest/build-tools/28.0.3:$PATH"
android_jar="/usr/lib/android-sdk/platforms/android-23/"

# generate the R.java file
echo "##### generate the R.java file"
aapt package -f -m -J ./src -M ./AndroidManifest.xml -S ./res -I ${android_jar}android.jar

# compile the .java files
echo "##### compile the .java files"
javac -source 1.7 -target 1.7 -d obj -classpath ./src -bootclasspath ${android_jar}android.jar src/com/jumhorn/webview/*.java src/com/jumhorn/textview/*.java

# translate .class to classes.dex
echo "##### translate .class to classes.dex"
dx --dex --output=./bin/classes.dex ./obj

# put everything in an APK
echo "##### put everything in an APK"
aapt package -f -m -F ./bin/unaligned.apk -M ./AndroidManifest.xml -S ./res -I ${android_jar}android.jar
cd ./bin
aapt add unaligned.apk classes.dex
cd ..

# align the package
echo "##### align the package"
zipalign -f 4 ./bin/unaligned.apk ./bin/aligned.apk

# sign the package
echo "##### sign the package"
apksigner sign --ks release.keystore -v1-signing-enabled true -v2-signing-enabled true --ks-pass pass:password --out ./bin/app.apk ./bin/aligned.apk