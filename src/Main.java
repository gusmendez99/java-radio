import com.example.models.Radio;
import com.example.models.MyRadio;

import java.util.Scanner;

public class Main {
    private static String getMenu() {
        return "Menu:\n" +
                "\t1. Encender Radio\n" +
                "\t1. Encender Radio\n" +
                "\t2. Cambiar Frecuencia (AM/FM).\n" +
                "\t3. Cambiar de estación.\n" +
                "\t4. Guardar emisora Actual\n" +
                "\t5. Sintonizar una emisora de un botón\n" +
                "\t6. Apagar el Radio\n";

    }

    public static void main(String[] args) {
        Radio radio = new MyRadio();
        System.out.print(radio.toString());
        int selection = 0;
        do {
            System.out.println(getMenu());
            System.out.println("Ingrese su selección: ");
            Scanner strSelection = new Scanner(System.in);
            boolean okSelection = false;

            try {
                selection = strSelection.nextInt();
                okSelection = true;

            } catch (Exception e){
                System.out.println("Ingrese un número de la lista: ");
            }
            if (okSelection) {
                selection = strSelection.nextInt();

                switch (selection){
                    case 1:
                        System.out.print("ha seleccionado 1");

                        break;
                    case 2:
                        System.out.println("ha seleccionado 2");
                        break;
                }

            }


        } while (selection != 6);

    }
}
