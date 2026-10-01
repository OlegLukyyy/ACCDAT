package ejercicios1_FicherosDeTexto;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Ejercicio4 {

    /*
    Realiza un programa que dado un fichero de texto, se copie en tres ficheros diferentes de tal manera que
    copie en el primer fichero los primeros 5 caracteres, en el segundo, los 5 siguientes y en el tercero los
    5 siguientes, y así sucesivamente hasta copiar todo el fichero. Utilizar lectura sin desplazamiento y
    escrituras con desplazamiento de un buffer de 15 caracteres. Después, hacer justamente lo contrario.
    Dados los 3 ficheros, construir uno como el fichero original. Utilizar ahora lecturas con desplazamiento
    y escritura sin desplazamiento de un buffer de 15 caracteres. Comprobar por código que ambos son iguales.
    En el caso de la última lectura/escritura, si no se llena el buffer, utilizar el desplazamiento para no dejar basura.
     */


    public static void main(String[] args) {
    new Ejercicio4().show();
    }

public void show(){
    System.out.println(" ");
}

public void run(){

    Path desktopPath = Paths.get(System.getProperty("user.home") + File.separator+"Desktop");


}
}