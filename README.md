# make website to android app

create simple project without android studio

# build the app

## generate the R.java file
```shell
# -m instructs aapt to create directories under the location specified by -J
# -J specifies where the output goes. Saying -J src will create a file like src/com/example/helloandroid/R.java
# -S specifies where is the res directory with the drawables, layouts, etc
# -I tells aapt where the android.jar is. You can find yours in a location like android-sdk/platforms/android-<API level>/android.jar
aapt package -f -m -J ./src -M ./AndroidManifest.xml -S ./res -I /opt/android-sdk/platforms/android-31/android.jar
```

## compile the .java files
```shell
# compile .class file to obj folder
javac -source 1.7 -target 1.7 -d obj -classpath ./src -bootclasspath /opt/android-sdk/platforms/android-31/android.jar src/com/android/app/*.java
```

## translate .class to classes.dex
```shell
dx --dex --output=./bin/classes.dex ./obj
```

## put everything in an APK
```shell
# generate apk file
aapt package -f -m -F ./bin/unaligned.apk -M ./AndroidManifest.xml -S ./res -I /opt/android-sdk/platforms/android-31/android.jar

# You have to copy the classes.dex file at the root of project Otherwise, AAPT won’t put this file at right place in the APK archive (because an APK is like a .zip file)
cp ./bin/classes.dex .
aapt add ./bin/unaligned.apk classes.dex

# check the apk file
aapt list ./bin/unaligned.apk
```

# sign the package
```shell
keytool -genkeypair -validity 365 -keystore release.keystore -keyalg RSA -keysize 2048

apksigner sign --ks release.keystore ./bin/unaligned.apk
```
# align the package
```shell
# Alignment increase the performance of the application and may reduce memory use.
zipalign -f 4 ./bin/unaligned.apk ./bin/app.apk
```

# other files

## build.sh

shell script to build this app

# roadmap

- [x] step by step build this app
- [ ] use webview to embed website
- [ ] expose android api to javascript

# FAQ

* unsupported class file version 55.0

	make java to build 1.7 compatible .class file

* java.io.FileNotFoundException: ./bin/classes.dex

	create bin folder for dx to put classes.dex
