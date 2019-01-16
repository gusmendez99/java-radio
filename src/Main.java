import com.example.models.Radio;
import com.example.models.MyRadio;

import java.util.Scanner;

public class Main {
    private static String getMenu() {
        return "Menu:\n" +
                "\t1. Encender Radio\n" +
                "\t2. Cambiar Frecuencia (AM/FM).\n" +
                "\t3. Cambiar de estación.\n" +
                "\t4. Guardar emisora Actual\n" +
                "\t5. Sintonizar una emisora de un botón\n" +
                "\t6. Apagar el Radio\n";

    }

    public static void main(String[] args) {
        Radio radio = new MyRadio();
        int selection;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println(radio);
            System.out.println(getMenu());
            System.out.print("Ingrese su selección: ");
            selection = scanner.nextInt();

            switch (selection) {
                case 1:
                    if (!((MyRadio) radio).isTurnedOn()) {
                        radio.toggle();
                    } else {
                        System.out.println("El Radio ya se encuentra encendido");
                    }
                    break;
                case 2:
                    radio.changeFrequency();
                    System.out.println("El radio se encuentra en " + ((radio.getFrequency() ? "FM" : "AM")));
                    break;
                case 3:
                    System.out.print("Ingrese 1 para avanzar de estación o 0 para retroceder: ");
                    int stepSelection = scanner.nextInt();

                    switch(stepSelection){
                        case 0:
                            radio.changeStation(false);
                            break;
                        case 1:
                            radio.changeStation(true);
                            break;
                        default:
                            System.out.println("Opcion incorrecta, intenta de nuevo...");
                            break;
                    }
                    break;
                case 4:
                    System.out.print("¿En qué posición desea guardar la emisora? (1-12): ");
                    int buttonSaveSelection = scanner.nextInt();
                    if(buttonSaveSelection > 0 && buttonSaveSelection <= 12){
                        radio.saveStation(buttonSaveSelection); //TODO: Check if station slot isn't empty
                        System.out.println("Radio salvada exitosamente en el boton " + buttonSaveSelection + "!");
                    } else {
                        System.out.println("Posicion invalida, intenta de nuevo...");
                    }
                    break;
            }

        } while (selection != 6);

        System.out.println("Hasta pronto...");

    }
}
