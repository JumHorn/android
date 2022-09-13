#!/bin/bash
# add environment path
export PATH="/opt/android-sdk/cmdline-tools/latest/build-tools/28.0.3:$PATH"

# generate the R.java file
echo "##### generate the R.java file"
aapt package -f -m -J ./src -M ./AndroidManifest.xml -S ./res -I /opt/android-sdk/platforms/android-31/android.jar

# compile the .java files
echo "##### compile the .java files"
javac -source 1.7 -target 1.7 -d obj -classpath ./src -bootclasspath /opt/android-sdk/platforms/android-31/android.jar src/com/jumhorn/app/*.java

# translate .class to classes.dex
echo "##### translate .class to classes.dex"
dx --dex --output=./bin/classes.dex ./obj

# put everything in an APK
echo "##### put everything in an APK"
aapt package -f -m -F ./bin/unaligned.apk -M ./AndroidManifest.xml -S ./res -I /opt/android-sdk/platforms/android-31/android.jar
cd ./bin
aapt add unaligned.apk classes.dex
cd ..

# align the package
echo "##### align the package"
zipalign -f 4 ./bin/unaligned.apk ./bin/aligned.apk

# sign the package
echo "##### sign the package"
apksigner sign --ks release.keystore -v1-signing-enabled true -v2-signing-enabled true --ks-pass pass:password --out ./bin/app.apk ./bin/aligned.apk