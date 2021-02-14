@ECHO OFF
TITLE BOMBERMAN

javac Constants/Constants.java game/data/*.java game/object/*.java game/Additions/*.java game/Ranking/*.java game/ServerConnection/Client.java game/panel/*.java *.java


jar cvfm BombermanPlay.jar META-INF/MANIFEST.MF *.class Constants/Constants.class game/data/*.class game/object/*.class game/Additions/*.class game/Ranking/*.class game/ServerConnection/Client.class game/panel/*.class 

