# Proggetto OOP 
Lo scopo di questo progetto è di sviluppare un applicazione SpringBoot che data una città cercata a piacimento dall'utente tramite l'utilizzo di due API.Con l'uso della prima [5 days weather forecast](https://openweathermap.org/forecast5#name5) si visualizzino tutte le informazioni attuali e future relative all'umidità dei successivi 5 giorni e con la seconda [current weather data](https://openweathermap.org/current) si salvino ogni ora le informazioni relative all'umidità e le temperature(massime, minime, reali e percepite).

L'utente grazie a [Postman](https://learning.postman.com/docs/getting-started/introduction/), puo utilizzare le funzionalità dell'applicazione Spring che con la sua dipendenza "spring-boot-starter-web" riesce ad avviare facilmente un server Tomcat embedded   

#### INDICE 
(da fare)

# L'applicazione
L'applicazione è strutturata in modo che dopo la call all'api 5 days weather forecast prenda i valori restituiti da essa e li salvi su un vettore statico(in modo che siano accessibili anche dall'endpoint /getHumidityStats).

Invece per quanto riguarda la call all'api current weather data , la risposta viene salvata in stringa su file in formato json tramite l'utilizzo di un vettore statico ogni ora (grazie all'utilizzo di un timer). All'avvio dell'endpoint /startCurrentService l'applicazione verificherà se ci siano o meno dei valori nel file, se già esistenti li caricherà in un vettore. In caso ci siano gia valori nel file,l'applicazione verificherà che l'id della città già salvata in precedenza sia lo stesso della città passata come parametro a Postman.Dopodichè si creerà un javaObject contenente i valori correnti delle previsioni(restituiti dall'api) e si salveranno all'interno del vettore. Ora avremo un vettore popolato da javaObject che verrà salvato su un file in formato JSON(grazie all'utilizzo di un metodo che farà il parsing degli elementi del vettore da JavaObject a JSON). 

### Come funziona: ROTTE DISPONIBILI

| Rotta         |    Metodo    |        Funzione                        |
|---------------|--------------|----------------------------------------|
| /getCompleteForecast    | GET        | Avvia ricerca e mostra dati per i prossimi 5 giorni ogni 3 ore |
| /startCurrentService| GET      | Avvia il salvataggio dei dati correnti ogni ora     |
| /stopCurrentService         | GET          | Ferma il salvataggio dei dati correnti        |
| /getHumidityStats  | GET          | Crea statistiche relative all'umidità con i dati già presenti   |
| /filter/daily/{initialValue}/{finalValue}/{days}  | GET          | Filtra le statistiche dei dati salvati ogni ora giornalmente|
| /filter/weekly/{initialValue}/{finalValue}    | GET       |  Filtra le statistiche dei dati salvati ogni ora settimanalmente    |


# GET /getCompleteForecast

In questa rotta è possibile inserire un parametro di tipo "nome" per cercare il nome della città scelta. Di default il "nome" è impostato su "Roma".

#### ESEMPIO: 
| KEY       |    VALUE    | 
|---------------|--------------|
|nome  | Milan  


<details>
<summary>MODEL</summary>
<br>

```
{
"city": {
"lat": 45.4643,
"lon": 9.1895,
"country": null,
"cityName": "Milan",
"id": 3173435
},
"forecast5DaysVectorHum": [
{
"humidity": {
"value": 84,
"unit": "%"
},
"dayTime": 1641146400
},
{
"humidity": {
"value": 78,
"unit": "%"
},
"dayTime": 1641157200
},
{
"humidity": {
"value": 80,
"unit": "%"
},
"dayTime": 1641168000
},
{
"humidity": {
"value": 89,
"unit": "%"
},
"dayTime": 1641178800
},
{
"humidity": {
"value": 94,
"unit": "%"
},
"dayTime": 1641189600
},
{
"humidity": {
"value": 82,
"unit": "%"
},
"dayTime": 1641200400
},
{
"humidity": {
"value": 65,
"unit": "%"
},
"dayTime": 1641211200
},
{
"humidity": {
"value": 71,
"unit": "%"
},
"dayTime": 1641222000
},
{
"humidity": {
"value": 89,
"unit": "%"
},
"dayTime": 1641232800
},
{
"humidity": {
"value": 96,
"unit": "%"
},
"dayTime": 1641243600
},
{
"humidity": {
"value": 94,
"unit": "%"
},
"dayTime": 1641254400
},
{
"humidity": {
"value": 90,
"unit": "%"
},
"dayTime": 1641265200
},
{
"humidity": {
"value": 87,
"unit": "%"
},
"dayTime": 1641276000
},
{
"humidity": {
"value": 83,
"unit": "%"
},
"dayTime": 1641286800
},
{
"humidity": {
"value": 82,
"unit": "%"
},
"dayTime": 1641297600
},
{
"humidity": {
"value": 83,
"unit": "%"
},
"dayTime": 1641308400
},
{
"humidity": {
"value": 83,
"unit": "%"
},
"dayTime": 1641319200
},
{
"humidity": {
"value": 88,
"unit": "%"
},
"dayTime": 1641330000
},
{
"humidity": {
"value": 94,
"unit": "%"
},
"dayTime": 1641340800
},
{
"humidity": {
"value": 97,
"unit": "%"
},
"dayTime": 1641351600
},
{
"humidity": {
"value": 97,
"unit": "%"
},
"dayTime": 1641362400
},
{
"humidity": {
"value": 91,
"unit": "%"
},
"dayTime": 1641373200
},
{
"humidity": {
"value": 84,
"unit": "%"
},
"dayTime": 1641384000
},
{
"humidity": {
"value": 87,
"unit": "%"
},
"dayTime": 1641394800
},
{
"humidity": {
"value": 89,
"unit": "%"
},
"dayTime": 1641405600
},
{
"humidity": {
"value": 90,
"unit": "%"
},
"dayTime": 1641416400
},
{
"humidity": {
"value": 79,
"unit": "%"
},
"dayTime": 1641427200
},
{
"humidity": {
"value": 74,
"unit": "%"
},
"dayTime": 1641438000
},
{
"humidity": {
"value": 68,
"unit": "%"
},
"dayTime": 1641448800
},
{
"humidity": {
"value": 66,
"unit": "%"
},
"dayTime": 1641459600
},
{
"humidity": {
"value": 50,
"unit": "%"
},
"dayTime": 1641470400
},
{
"humidity": {
"value": 47,
"unit": "%"
},
"dayTime": 1641481200
},
{
"humidity": {
"value": 67,
"unit": "%"
},
"dayTime": 1641492000
},
{
"humidity": {
"value": 71,
"unit": "%"
},
"dayTime": 1641502800
},
{
"humidity": {
"value": 68,
"unit": "%"
},
"dayTime": 1641513600
},
{
"humidity": {
"value": 66,
"unit": "%"
},
"dayTime": 1641524400
},
{
"humidity": {
"value": 68,
"unit": "%"
},
"dayTime": 1641535200
},
{
"humidity": {
"value": 65,
"unit": "%"
},
"dayTime": 1641546000
},
{
"humidity": {
"value": 63,
"unit": "%"
},
"dayTime": 1641556800
},
{
"humidity": {
"value": 64,
"unit": "%"
},
"dayTime": 1641567600
}
]
}
```

</details>


I campi del JSON sopraindicato rappresentano.

* **"city"** è il JSONObject che contiene le informazioni riguardanti la città:
  * **"lat"** è la latitudine
  * **"lon"** è la longitudine
  * **"country"** è lo stato della città
  * **"cityName"** è il nome della città
  * **"id"** è l'id della città
* **"forecast5DaysVectorHum"** è il JSONArray che contiene tutti i dati delle previsioni
   * **"humidity"** è il JSONObject che contiene le informazioni riguardanti l'umidità
     * **"value"** è il valore dell'umiditià
     * **"unit"** è l'unità di misura
    * **"dt"** è l'orario del giorno della previsione

# GET /startCurrentService 
Salva localmente in un file ".json" la risposta di Postman ogni ora grazie ad un timer.

In questa rotta è possibile inserire un parametro di tipo "nome" per cercare il nome della città scelta. Di default il "nome" è impostato su "Roma".

#### ESEMPIO: 
| KEY       |    VALUE    | 
|---------------|--------------|
|nome  | Milan  |

<details>
<summary>MODEL</summary>
<br>

```
[
    {
        "city": {
            "ID": 3183087,
            "cityName": "\"Provincia di Ancona\"",
            "lat": 13.1667,
            "lon": 43.55
        },
        "dt": 1641124445,
        "humidity": { "value": 100 },
        "temperature": {
            "temp": 7.78,
            "tempMin": 5.1,
            "tempMax": 10.22,
            "tempFeel": 7.78
        }
    },
] 
```

</details>


* **"city"** è il JSONObject che contiene le informazioni riguardanti la città:
  * **"lat"** è la latitudine
  * **"lon"** è la longitudine
  * **"cityName"** è il nome della città
  * **"id"** è l'id della città
 * **"dt"** è l'orario del giorno della previsione
 * **"humidity"** è il JSONObject che contiene le informazioni riguardanti l'umidità:
  **"value"** è il valore dell'umidità
 * **"temperature"** è il JSONObject che contiene le informazioni riguardanti le temperature:
    * **"temp"** è la temperatura reale
    * **"tempMin"** è la temperatura minima
    * **"tempMax"** è la temperatura massima
    * **"tempFeel"** è la temperatura percepita

# GET /stopCurrentService

blocca il salvataggio dei dati.

# GET /getHumidityStats

Crea statistiche riguardanti l'umidità nei prossimi 5 giorni.

<details>
<summary>MODEL</summary>
<br>

```
{
"city": {
"lat": 45.4643,
"lon": 9.1895,
"country": null,
"cityName": "Milan",
"id": 3173435
},
"umiditaMinimaAssoluta": 34.0,
"umiditaMassimaAssoluta": 98.0,
"mediaUmidità": 74.875
}
```

</details>

# GET /filter/daily/{initialValue}/{finalValue}/{days} e /filter/weekly/{initialValue}/{finalValue} 

\\TODO\\

# ECCEZIONI:
L'applicazione può lanciare diverse eccezioni alcune standard e altre personalizzate:

PERSONALIZZATE:

* **IllegalTimeException**: Eccezione nel caso vengano inseriti parametri sbagliati nei filtri.
* **NoAPIResponseException**: Eccezione nel caso l'api risponda con "null".
* **StopNotValidException**: Eccezione nel caso venga lanciata la rotta stopCurrentService prima di StartCurrentService.

STANDARD:

* IOException
* ParseException
* Exception
* NullPointerException
* IllegalArgumentException




# SOFTWARE UTILIZZATI: 



