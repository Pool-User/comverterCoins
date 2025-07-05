import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class Monedas {

    @SerializedName("conversion_rates")
    private Map<String, Double> mapMonedas;

    public Map<String, Double> getMapMonedas() {
        return mapMonedas;
    }

    public void setmMpMonedas(Map<String, Double> mapMonedas) {
        this.mapMonedas = mapMonedas;
    }

    // Método para obtener el valor de conversión de una moneda específica
    public Double getMoneda(String moneda) {
        return mapMonedas.get(moneda);
    }
}
