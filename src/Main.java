import com.example.models.Radio;
import com.example.models.MyRadio;

import java.util.Scanner;

public class Main {
    /**
     * Builds the string menu to show depending on whether the radio´s state is ON or OFF
     * @param isTurnedOn true if radio is already turned on, false otherwise
     * @return the string of the menu
     */
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
        Radio radio = new MyRadio(); //You can change the class of your preference to test the interface and the program itself
        int selection;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println(radio);
            System.out.println(getMenu(radio.getState()));
            System.out.print("Ingrese su selección: ");
            selection = scanner.nextInt(); // the integer of the scanner is assigned to selection

            if(!radio.getState() && selection > 1) { //User cannot change radio frequency if radio isn't on yet
                System.out.println("Estas opciones no estan disponibles, por favor, enciende la radio primero");
            } else {
                switch (selection) {
                    case 1: //when the user selects to turn on radio
                        if (!radio.getState()) {
                            radio.toggle(); //changes the actual state of rhe radio
                        } else {
                            System.out.println("El Radio ya se encuentra encendido");
                        }
                        break;
                    case 2: //User wants to change the frequency from AM/FM
                        radio.changeFrequency(); //toggles between AM/FM
                        System.out.println("El radio se encuentra en " + ((radio.getFrequency() ? "FM" : "AM")));
                        break;
                    case 3:
                        System.out.print("Ingrese 1 para avanzar de estación o 0 para retroceder: ");
                        int stepSelection = scanner.nextInt(); //saves the int for increase or decrease of the station

                        switch(stepSelection){
                            case 0: //user wants to decrease the station, change station = false
                                radio.changeStation(false);
                                break;
                            case 1://user wants to decrease the station, change station = true
                                radio.changeStation(true);
                                break;
                            default:
                                System.out.println("Opcion incorrecta, intenta de nuevo...");
                                break;
                        }
                        break;
                    case 4: //User wants to save a station in one of the 12 available buttons
                        System.out.print("¿En qué posición desea guardar la emisora? (1-12): ");
                        int buttonSaveSelection = scanner.nextInt(); //saves the number of the button where the user wants to save the station
                        if(buttonSaveSelection > 0 && buttonSaveSelection <= 12){ //verify if buttonSaveSelection is in range 1..12
                            radio.saveStation(buttonSaveSelection); //Guardala estación actual en el botón indicado.
                            System.out.println("Radio salvada exitosamente en el boton " + buttonSaveSelection + "!");
                        } else {
                            System.out.println("Posicion invalida, intenta de nuevo...");
                        }
                        break;
                    case 5:
                        System.out.print("¿Que boton desea presionar? (1-12): ");
                        int buttonSetSelection = scanner.nextInt(); //saves the number of button to change the current station
                        if(buttonSetSelection > 0 && buttonSetSelection <= 12){  //verify if buttonSaveSelection is in range 1..12
                            radio.changeStationButton(buttonSetSelection); //sets current radio station to the one saved in the number of button provided by the user
                            System.out.println("Has cambiado la estacion actual!");
                        } else {
                            System.out.println("Posicion invalida, intenta de nuevo...");
                        }
                        break;
                    case 6: //Changes the state to OFF
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
