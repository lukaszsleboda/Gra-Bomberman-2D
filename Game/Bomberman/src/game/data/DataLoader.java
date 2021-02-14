package game.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * Definicja klasa ladujacej dane z pliku
 */
public class DataLoader
{
    String result = "";
    InputStream inputStream;

    /**
     * Metoda pobierajaca dane z plikow tekstowych
     * @param key klucz zmiennej
     * @param File plik z ktorego bedziemy wczytywac
     * @return wartosc pod kluczem w pliku tekstowym
     * @throws IOException wyjatek
     */
    public String getPropertyValue(String key, String File) throws IOException {

        try
        {
            Properties properties = new Properties();

            inputStream = getClass().getClassLoader().getResourceAsStream(File);

                if (inputStream == null)
                {
                    throw new FileNotFoundException("Problem z plikiem: " + File);
                }
                else
                {
                    properties.load(inputStream);
                }

            result = properties.getProperty(key);

        }

        catch (Exception e)
        {
            System.out.println("Exception: " + e);
        }

        finally
        {
            inputStream.close();
        }

        return result;
    }

}
