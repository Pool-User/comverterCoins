import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public void subInterfaz(String comverter){
        System.out.println("-------- COMVERTIR " + comverter + " --------");
        System.out.println("MONEDAS DISPONIBLES: ");
        System.out.println("[1] PESO ARGENTINO");
        System.out.println("[2] REAL BRASILEÑO");
        System.out.println("[3] PESO COLOMBIANO");
        System.out.println("----------------------------------------------");
    }

    public String resultDesiscion(int opMin, int opMax, int opcionUser){
        String resultado = "N";
        if (opcionUser <= opMin || opcionUser > opMax){
            System.out.println(">>>SYSTEM: POR FAVOR, INTENTELO NUEVAMENTE!!!!! :)");
            resultado = "S";
            return resultado;
        } else {
            return resultado;
        }
    }

    public boolean resultCantidad(double opMin, double opcionUser){
        boolean resultado = false;
        if (opcionUser <= opMin){
            System.out.println(">>>SYSTEM: INGRESO UN VALOR NO VALIDO, POR FAVOR, INTENTELO NUEVAMENTE!!!!! :(");
            resultado = true;
            return resultado;
        } else {
            return resultado;
        }
    }

    public double comvertidorMoneda(String monedaIn, String monedaOut, double cantidad) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://v6.exchangerate-api.com/v6/3e4af89bb52cd6bfe87454af/latest/"+monedaOut))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();
        Gson gson = new Gson();
        Monedas monedas = gson.fromJson(json, Monedas.class);
        double monedaComvertir = monedas.getMoneda(monedaIn); // soles 3.54
        double codeMonedaComvertir = monedas.getMoneda(monedaOut); //dollar 1
        double monedaConvertida = (cantidad * codeMonedaComvertir) / monedaComvertir;

        return monedaConvertida;
    }
    public void main(String[] args) throws IOException, InterruptedException {
        Scanner input = new Scanner(System.in);
        int opcionUser, subOpcionUser;
        String desicionMainUser = "S";
        String desicionSubUser;
        String monedasIn = "NULL";
        String monedasOut = "NULL";
        double cantidadComvertir;

        while (desicionMainUser.equals("S")) {
            System.out.println("-------- COMVERSOR DE MONEDAS --------");
            System.out.println("[1] COMVERTIR SU MONEDA A DOLLAR");
            System.out.println("[2] COMVERTIR DOLLAR A SU MONEDA");
            System.out.println("[3] SALIR");
            System.out.println("--------------------------------------");
            System.out.println(">>>SELECCIONE OPCION: ");
            opcionUser = input.nextInt();

            while (opcionUser <= 0 || opcionUser > 3) {
                System.out.println(">>>SYSTEM: LA OPCION QUE SELECCIONO NO ESTA ENTRE LAS OPCIONES MOSTRADAS :(");
                System.out.println(">>>SYSTEM: POR FAVOR, INTENTELO NUEVAMENTE :)");
                System.out.println("---------------------------------------------------------------------------");
                System.out.println(">>>SELECCIONE OPCION: ");
                opcionUser = input.nextInt();
                input.nextLine();
            }

            if (opcionUser == 1) {
                desicionSubUser = "S";
                while (desicionSubUser.equals("S")) {
                    subInterfaz("SU MONEDA A DOLLAR");
                    System.out.println(">>>SELECCIONE MONEDA A COMVERTIR: ");
                    subOpcionUser = input.nextInt();
                    input.nextLine();
                    if (resultDesiscion(0,3,subOpcionUser).equals("S")){
                        desicionSubUser = "S";
                    } else {
                        monedasOut = "USD";
                        if (subOpcionUser == 1){ monedasIn = "ARS"; }
                        else if (subOpcionUser == 2){ monedasIn = "BRL"; }
                        else if (subOpcionUser == 3){ monedasIn = "COP"; }
                        System.out.println(">>>INGRESE CANTIDAD A COMVERTIR: ");
                        cantidadComvertir = input.nextDouble();
                        if (resultCantidad(0, cantidadComvertir) == true) { desicionSubUser = "S"; }
                        else {
                            System.out.println(cantidadComvertir + " " + monedasIn + " EQUIVALE A " +
                                    comvertidorMoneda(monedasIn, monedasOut, cantidadComvertir) + " " + monedasOut);
                        }
                        input.nextLine();
                        System.out.println(">>>SYSTEM: DESEA COMVERTIR OTRA MONEDA A DOLLAR?: ");
                        desicionSubUser = input.nextLine().toUpperCase();
                    }
                }
            } else if (opcionUser == 2) {
                desicionSubUser = "S";
                while (desicionSubUser.equals("S")) {
                    subInterfaz("DOLLAR A SU MONEDA");
                    System.out.println(">>>SELECCIONE MONEDA A COMVERTIR: ");
                    subOpcionUser = input.nextInt();
                    input.nextLine();
                    if (resultDesiscion(0,3,subOpcionUser).equals("S")){
                        desicionSubUser = "S";
                    } else {
                        monedasIn = "USD";
                        if (subOpcionUser == 1){ monedasOut = "ARS"; }
                        else if (subOpcionUser == 2){ monedasOut = "BRL"; }
                        else if (subOpcionUser == 3){ monedasOut = "COP"; }
                        System.out.println(">>>INGRESE CANTIDAD A COMVERTIR: ");
                        cantidadComvertir = input.nextDouble();
                        if (resultCantidad(0, cantidadComvertir) == true) { desicionSubUser = "S"; }
                        else {
                            System.out.println(cantidadComvertir + " " + monedasIn + " EQUIVALE A " +
                                    comvertidorMoneda(monedasIn, monedasOut, cantidadComvertir) + " " + monedasOut);
                        }
                        input.nextLine();
                        System.out.println(">>>SYSTEM: DESEA COMVERTIR DOLLAR A OTRA MONEDA?: ");
                        desicionSubUser = input.nextLine().toUpperCase();
                    }
                }
            } else if (opcionUser == 3) {
                System.out.println(">>>SYSTEM: BYE, HASTA QUE ME EJECUTES NUEVAMENTE ;)");
                desicionMainUser = "N";
            }
        }
    }
}