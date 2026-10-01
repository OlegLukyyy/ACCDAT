package ejercicios1_FicherosDeTexto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Stream;

public class Ejercicio3 {
    /*
Realiza este ejercicio utilizando las clases BufferedReader y BufferedWriter: concatena todos tus ficheros
.java de este boletín en uno solo utilizando código de programación.
A continuación, lee dicho fichero y genera dos ficheros:

Fichero donde estén las declaraciones realizadas en los paréntesis del try-with-resources, cada una en una línea y terminadas con ;. Ejemplo:


Stream<Path> directoryContents;
BufferedReader reader;
BufferedWriter writer;
Fichero donde estén las declaraciones de las distintas excepciones tratadas, cada una en una línea, terminadas con ; y sin repeticiones, es decir, la excepción que aparezca varias veces que solamente aparezca una vez en el fichero:


FileNotFoundException e;
IOException e;
FileAlreadyExistsException e;
NoSuchFileException e;
NotDirectoryException e;
     */

    public static void main(String[] args) {
        new Ejercicio3().show();
    }

    public void show() {
        Path combined = Paths.get("C:\\Users\\lukia\\Desktop\\combined.txt");
        joinJavaFiles(combined);
        filterJavaFile(combined, Paths.get("C:\\Users\\lukia\\Desktop"));
    }

    public List<Path> getJavaFiles() {
        Path currentDir = Paths.get("src");

        try (Stream<Path> directoryContents = Files.walk(currentDir)) {
            return directoryContents
                    .filter(Files::isRegularFile)
                    .filter(file -> file.toString().endsWith(".java"))
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void joinJavaFiles(Path destination) {

        List<Path> toJoin = getJavaFiles();
        try (BufferedWriter writer = Files.newBufferedWriter(
                destination,
                StandardOpenOption.APPEND,
                StandardOpenOption.CREATE
        );) {
            for (Path source : toJoin) {
                try (BufferedReader reader = Files.newBufferedReader(source);) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        writer.write(line);
                        writer.newLine();
                    }
                } catch (IOException e) {
                    System.err.printf("Error de entrada/salida: %s", e.getMessage());
                }

            }
        } catch (IOException e) {
            System.err.printf("El fichero no existe: %s", e.getMessage());
        }

    }

    public void filterJavaFile(Path source, Path destination) {
        String line;
        try (BufferedReader reader = Files.newBufferedReader(source);
             BufferedWriter writerTry = Files.newBufferedWriter(
                     destination.resolve("try.txt"),
                     StandardOpenOption.APPEND,
                     StandardOpenOption.CREATE
             );
             BufferedWriter writerCatch = Files.newBufferedWriter(
                     destination.resolve("catch.txt"),
                     StandardOpenOption.APPEND,
                     StandardOpenOption.CREATE
             );
        ) {
            while ((line = reader.readLine()) != null) {
                if (line.matches("(?s)\\btry\\s*\\(([^)]+)\\)")) {
                    writerTry.write(line);
                }
                if (line.matches("(?s)\\bcatch\\s*\\(([^)]+)\\)")) {
                    writerCatch.write(line);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.printf("El fichero no existe: %s", e.getMessage());
        } catch (Exception e) {
            System.err.printf("Error de entrada/salida: %s", e.getMessage());
        }

    }

}
