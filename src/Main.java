import com.example.models.Radio;
import com.example.models.MyRadio;

import java.util.Scanner;

public class Main {
    private static String getMenu(boolean isTurnedOn) {
        if(isTurnedOn){
            return "Menu:\n" +
                    "\t1. Encender Radio\n" +
                    "\t2. Cambiar Frecuencia (AM/FM).\n" +
                    "\t3. Cambiar de estación.\n" +
                    "\t4. Guardar emisora Actual\n" +
                    "\t5. Sintonizar una emisora de un botón\n" +
                    "\t6. Apagar el Radio\n";
        } else {
            return "Menu:\n\t1. Encender Radio\n";
        }
    }

    public static void main(String[] args) {
        Radio radio = new MyRadio();
        int selection;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println(radio);
            System.out.println(getMenu(radio.getState()));
            System.out.print("Ingrese su selección: ");
            selection = scanner.nextInt();

            if(!radio.getState() && selection > 1) { //User cannot change radio frequency if radio isn't on yet
                System.out.println("Estas opciones no estan disponibles, por favor, enciende la radio primero");
            } else {
                switch (selection) {
                    case 1:
                        if (!radio.getState()) {
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
                    case 5:
                        System.out.print("¿Que boton desea presionar? (1-12): ");
                        int buttonSetSelection = scanner.nextInt();
                        if(buttonSetSelection > 0 && buttonSetSelection <= 12){
                            radio.changeStationButton(buttonSetSelection);
                            System.out.println("Has cambiado la estacion actual!");
                        } else {
                            System.out.println("Posicion invalida, intenta de nuevo...");
                        }
                        break;
                    case 6:
                        if (radio.getState()) {
                            radio.toggle();
                        }
                        break;
                }
            }


        } while (selection != 6);
        System.out.println("Hasta pronto...");

    }
}
