import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress game1 = new GameProgress(100,1,1,1);
        GameProgress game2 = new GameProgress(50, 2, 5, 24);
        GameProgress game3 = new GameProgress(43, 3, 50, 132);

        saveGame("D:/Games/savegames/save1.dat", game1);
        saveGame("D:/Games/savegames/save2.dat", game2);
        saveGame("D:/Games/savegames/save3.dat", game3);

        List<String> list = new ArrayList<>();
        list.add("D:/Games/savegames/save1.dat");
        list.add("D:/Games/savegames/save2.dat");
        list.add("D:/Games/savegames/save3.dat");

        zipFiles("D:/Games/savegames/games.zip", list);

        for (String path : list) {
            deleteFile(path);
        }

    }

    public static void deleteFile(String filePath) {
        File file = new File(filePath);
        file.delete();
    }


    public static void saveGame(String file, GameProgress game) {
        try {
            FileOutputStream stream1 = new FileOutputStream(file);
            ObjectOutputStream stream2 = new ObjectOutputStream(stream1);

            stream2.writeObject(game);

            stream1.close();
            stream2.close();
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void zipFiles(String zipFile, List<String> games){
        try {
            FileOutputStream stream1 = new FileOutputStream(zipFile);
            ZipOutputStream stream2 = new ZipOutputStream(stream1);

            for (String game : games) {

                FileInputStream inputStream = new FileInputStream(game);

                ZipEntry entry = new ZipEntry(game);
                stream2.putNextEntry(entry);

                int fileBytes;
                while ((fileBytes = inputStream.read()) != -1) {
                    stream2.write(fileBytes);
                }

                inputStream.close();
            }

            stream1.close();
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}