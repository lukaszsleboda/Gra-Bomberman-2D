@ECHO
TITLE SERVER

javac Display/*.java Ranking/*.java server/*.java Main.java

jar cvfm Serwer.jar META-INF/MANIFEST.MF Display/*.class Ranking/*.class server/*.class Main.class

