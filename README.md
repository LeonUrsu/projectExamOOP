
# Proggetto OOP 
Lo scopo di questo progetto è di sviluppare un applicazione SpringBoot che data una città cercata a piacimento dall'utente tramite l'utilizzo di due API.Con l'uso della prima [5 days weather forecast](https://openweathermap.org/forecast5#name5) si visualizzino tutte le informazioni attuali e future relative all'umidità dei successivi 5 giorni e con la seconda [current weather data](https://openweathermap.org/current) si salvino ogni ora le informazioni relative all'umidità e le temperature(massime, minime, reali e percepite).

L'utente grazie a [Postman](https://learning.postman.com/docs/getting-started/introduction/), puo utilizzare le funzionalità dell'applicazione Spring che con la sua dipendenza "spring-boot-starter-web" riesce ad avviare facilmente un server Tomcat embedded   

#### INDICE
<p>
     • <a href="#1">L'Applicazione</a><br>
     • <a href="#2">Rotte Disponibili</a><br>
     • <a href="#3">Eccezioni</a><br> 
</p>

# L'applicazione <a name="1"></a>
L'applicazione è strutturata in modo che dopo la call all'api 5 days weather forecast prenda i valori restituiti da essa e li salvi su un vettore statico(in modo che siano accessibili anche dall'endpoint /getHumidityStats).

Invece per quanto riguarda la call all'api current weather data , la risposta viene salvata in stringa su file in formato json tramite l'utilizzo di un vettore statico ogni ora (grazie all'utilizzo di un timer). All'avvio dell'endpoint /startCurrentService l'applicazione verificherà se ci siano o meno dei valori nel file, se già esistenti li caricherà in un vettore. In caso ci siano gia valori nel file,l'applicazione verificherà che l'id della città già salvata in precedenza sia lo stesso della città passata come parametro a Postman.Dopodichè si creerà un javaObject contenente i valori correnti delle previsioni(restituiti dall'api) e si salveranno all'interno del vettore. Ora avremo un vettore popolato da javaObject che verrà salvato su un file in formato JSON(grazie all'utilizzo di un metodo che farà il parsing degli elementi del vettore da JavaObject a JSON). 

### Come funziona: ROTTE DISPONIBILI <a name="2"></a>

| Rotta         |    Metodo    |        Funzione                        |
|---------------|--------------|----------------------------------------|
| <a href="#4">/getCompleteForecast</a>   | GET        | Avvia ricerca e mostra dati per i prossimi 5 giorni ogni 3 ore |
| <a href="#5">/startCurrentService</a> | GET      | Avvia il salvataggio dei dati correnti ogni ora     |
| <a href="#6">/stopCurrentService </a>   | GET          | Ferma il salvataggio dei dati correnti        |
| <a href="#7">/getHumidityStats </a>  | GET          | Crea statistiche relative all'umidità con i dati già presenti   |
| <a href="#8">/load/{City}</a> | GET  | Carica su un vettore i dati salvati in precedenza a seconda della città passata|
| <a href="#9">/filter/daily/{initialValue}/{finalValue} </a>    | GET          | Filtra le statistiche dei dati salvati ogni ora giornalmente|



# GET /getCompleteForecast  <a name="4"></a>
L'API restituisce valori per i prossimi 5 giorni ogni 3 ore. Questi valori vengono modellati a JavaObject di tipo ForecastData5Days. Quando la rotta verrà chiamata oltre a restituire i valori a POSTMAN(convertiti in JSON) verranno trattenuti nella memoria RAM grazie ad un vettore statico per poterne creare statistiche successivamente.

In questa rotta è possibile inserire un parametro di tipo "nome" per cercare il nome della città scelta. Di default il "nome" è impostato su "Roma". 
#### ESEMPIO: 
| KEY       |    VALUE    | 
|---------------|--------------|
|nome  | Milan  |


<details>
<summary>MODEL</summary>
<br>


    
```
"city": {
        "lat": 34.257,
        "lon": -85.1647,
        "country": null,
        "cityName": "Rome",
        "id": 4219762
    },
    "forecast5DaysVectorHum": [
        {
            "dtString": "15-01-2022 19:00:00",
            "humidity": {
                "value": 82,
                "unit": "%"
            },
            "dayTime": 1642269600
        },
        {
            "dtString": "15-01-2022 22:00:00",
            "humidity": {
                "value": 75,
                "unit": "%"
            },
            "dayTime": 1642280400
        },
        {
            "dtString": "16-01-2022 01:00:00",
            "humidity": {
                "value": 76,
                "unit": "%"
            },
            "dayTime": 1642291200
        },
        {
            "dtString": "16-01-2022 04:00:00",
            "humidity": {
                "value": 88,
                "unit": "%"
            },
            "dayTime": 1642302000
        },
        {
            "dtString": "16-01-2022 07:00:00",
            "humidity": {
                "value": 93,
                "unit": "%"
            },
            "dayTime": 1642312800
        },
        {
            "dtString": "16-01-2022 10:00:00",
            "humidity": {
                "value": 90,
                "unit": "%"
            },
            "dayTime": 1642323600
        },
        {
            "dtString": "16-01-2022 13:00:00",
            "humidity": {
                "value": 88,
                "unit": "%"
            },
            "dayTime": 1642334400
        },
        {
            "dtString": "16-01-2022 16:00:00",
            "humidity": {
                "value": 94,
                "unit": "%"
            },
            "dayTime": 1642345200
        },
        {
            "dtString": "16-01-2022 19:00:00",
            "humidity": {
                "value": 99,
                "unit": "%"
            },
            "dayTime": 1642356000
        },
        {
            "dtString": "16-01-2022 22:00:00",
            "humidity": {
                "value": 99,
                "unit": "%"
            },
            "dayTime": 1642366800
        },
        {
            "dtString": "17-01-2022 01:00:00",
            "humidity": {
                "value": 97,
                "unit": "%"
            },
            "dayTime": 1642377600
        },
        {
            "dtString": "17-01-2022 04:00:00",
            "humidity": {
                "value": 90,
                "unit": "%"
            },
            "dayTime": 1642388400
        },
        {
            "dtString": "17-01-2022 07:00:00",
            "humidity": {
                "value": 90,
                "unit": "%"
            },
            "dayTime": 1642399200
        },
        {
            "dtString": "17-01-2022 10:00:00",
            "humidity": {
                "value": 92,
                "unit": "%"
            },
            "dayTime": 1642410000
        },
        {
            "dtString": "17-01-2022 13:00:00",
            "humidity": {
                "value": 89,
                "unit": "%"
            },
            "dayTime": 1642420800
        },
        {
            "dtString": "17-01-2022 16:00:00",
            "humidity": {
                "value": 88,
                "unit": "%"
            },
            "dayTime": 1642431600
        },
        {
            "dtString": "17-01-2022 19:00:00",
            "humidity": {
                "value": 79,
                "unit": "%"
            },
            "dayTime": 1642442400
        },
        {
            "dtString": "17-01-2022 22:00:00",
            "humidity": {
                "value": 76,
                "unit": "%"
            },
            "dayTime": 1642453200
        },
        {
            "dtString": "18-01-2022 01:00:00",
            "humidity": {
                "value": 79,
                "unit": "%"
            },
            "dayTime": 1642464000
        },
        {
            "dtString": "18-01-2022 04:00:00",
            "humidity": {
                "value": 78,
                "unit": "%"
            },
            "dayTime": 1642474800
        },
        {
            "dtString": "18-01-2022 07:00:00",
            "humidity": {
                "value": 93,
                "unit": "%"
            },
            "dayTime": 1642485600
        },
        {
            "dtString": "18-01-2022 10:00:00",
            "humidity": {
                "value": 94,
                "unit": "%"
            },
            "dayTime": 1642496400
        },
        {
            "dtString": "18-01-2022 13:00:00",
            "humidity": {
                "value": 92,
                "unit": "%"
            },
            "dayTime": 1642507200
        },
        {
            "dtString": "18-01-2022 16:00:00",
            "humidity": {
                "value": 71,
                "unit": "%"
            },
            "dayTime": 1642518000
        },
        {
            "dtString": "18-01-2022 19:00:00",
            "humidity": {
                "value": 56,
                "unit": "%"
            },
            "dayTime": 1642528800
        },
        {
            "dtString": "18-01-2022 22:00:00",
            "humidity": {
                "value": 54,
                "unit": "%"
            },
            "dayTime": 1642539600
        },
        {
            "dtString": "19-01-2022 01:00:00",
            "humidity": {
                "value": 79,
                "unit": "%"
            },
            "dayTime": 1642550400
        },
        {
            "dtString": "19-01-2022 04:00:00",
            "humidity": {
                "value": 87,
                "unit": "%"
            },
            "dayTime": 1642561200
        },
        {
            "dtString": "19-01-2022 07:00:00",
            "humidity": {
                "value": 88,
                "unit": "%"
            },
            "dayTime": 1642572000
        },
        {
            "dtString": "19-01-2022 10:00:00",
            "humidity": {
                "value": 88,
                "unit": "%"
            },
            "dayTime": 1642582800
        },
        {
            "dtString": "19-01-2022 13:00:00",
            "humidity": {
                "value": 92,
                "unit": "%"
            },
            "dayTime": 1642593600
        },
        {
            "dtString": "19-01-2022 16:00:00",
            "humidity": {
                "value": 72,
                "unit": "%"
            },
            "dayTime": 1642604400
        },
        {
            "dtString": "19-01-2022 19:00:00",
            "humidity": {
                "value": 76,
                "unit": "%"
            },
            "dayTime": 1642615200
        },
        {
            "dtString": "19-01-2022 22:00:00",
            "humidity": {
                "value": 92,
                "unit": "%"
            },
            "dayTime": 1642626000
        },
        {
            "dtString": "20-01-2022 01:00:00",
            "humidity": {
                "value": 96,
                "unit": "%"
            },
            "dayTime": 1642636800
        },
        {
            "dtString": "20-01-2022 04:00:00",
            "humidity": {
                "value": 98,
                "unit": "%"
            },
            "dayTime": 1642647600
        },
        {
            "dtString": "20-01-2022 07:00:00",
            "humidity": {
                "value": 93,
                "unit": "%"
            },
            "dayTime": 1642658400
        },
        {
            "dtString": "20-01-2022 10:00:00",
            "humidity": {
                "value": 90,
                "unit": "%"
            },
            "dayTime": 1642669200
        },
        {
            "dtString": "20-01-2022 13:00:00",
            "humidity": {
                "value": 82,
                "unit": "%"
            },
            "dayTime": 1642680000
        },
        {
            "dtString": "20-01-2022 16:00:00",
            "humidity": {
                "value": 69,
                "unit": "%"
            },
            "dayTime": 1642690800
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
    * **"dayTime"** è l'orario del giorno della previsione espresso in UnixTime
    *  **"dtString"** è la data e l'ora del giorno della previsione espresso in GMT+1

# GET /startCurrentService <a name="5"></a>
Salva localmente in un file ".json" la risposta di Postman ogni ora grazie ad un timer. Definito l'intervallo del timer, quando la rotta verrà chiamata creerà un JavaObject che verrà passato ad un vettore statico. Quindi tutti i valori verranno memorizzati nella memoria RAM . Successivamente verrà convertito in JSONArray contenente tutti i JSONobject relativi alle previsioni del meteo salvati quando l'intervallo terminerà.

In questa rotta è possibile utilizzare un query params con KEY:"nome" e VALUE:"nome della città" per cercare il nome della città scelta. Di default il "nome" è impostato su "Roma".

#### ESEMPIO: 
| KEY       |    VALUE    | 
|---------------|--------------|
|nome  | Macerata |

<details>
<summary>MODEL</summary>
<br>

```
 {
        "temperature": {
            "temp": 2.7,
            "tempMin": -1.79,
            "tempMax": 4.92,
            "tempFeel": 2.7
        },
        "city": {
            "ID": 3174379,
            "cityName": "Provincia di Macerata",
            "lat": 13.1667,
            "lon": 43.2
        },
        "dt": 1642151414,
        "dtString": "14-01-2022 10:10:14",
        "humidity": { "value": 53 }
    }
     
```

</details>


* **"city"** è il JSONObject che contiene le informazioni riguardanti la città:
  * **"lat"** è la latitudine
  * **"lon"** è la longitudine
  * **"cityName"** è il nome della città
  * **"id"** è l'id della città
 * **"dt"** è l'orario del giorno della previsione espressa in UnixTime
 * **""dtString** è la data e ora della previsione espressa in GMT+1
 * **"humidity"** è il JSONObject che contiene le informazioni riguardanti l'umidità:
  **"value"** è il valore dell'umidità
 * **"temperature"** è il JSONObject che contiene le informazioni riguardanti le temperature:
    * **"temp"** è la temperatura reale
    * **"tempMin"** è la temperatura minima
    * **"tempMax"** è la temperatura massima
    * **"tempFeel"** è la temperatura percepita

# GET /stopCurrentService <a name="6"></a>

Blocca il salvataggio dei dati. Tecnicamente azzera l'intervallo del timer definito in precedenza.

# GET /getHumidityStats <a name="7"></a>
Questa rotta va utilizzata **esclusivamente** dopo aver chiamato l'endpoint  <a href = "#4">/getCompleteForecast</a><br> 
Crea statistiche riguardanti l'umidità nei prossimi 5 giorni. Prende il vettore statico riguardante le previsioni dei prossimi 5 giorni e ne crea statistiche sull'umidità minima e massima assoluta e la media di tutte le umidità.


<details>
<summary>MODEL</summary>
<br>

```
{
{
    "city": {
        "lat": 34.257,
        "lon": -85.1647,
        "country": null,
        "cityName": "Rome",
        "id": 4219762
    },
    "umiditaMinimaAssoluta": 54.0,
    "umiditaMassimaAssoluta": 99.0,
    "mediaUmidità": 85.1
}
```

</details>

* **"city"** è il JSONObject che contiene le informazioni riguardanti la città:
  * **"lat"** è la latitudine
  * **"lon"** è la longitudine
  * **"cityName"** è il nome della città
  * **"id"** è l'id della città
* **"umiditaMinimaAssoluta"** è il minor valore dell'umidità che si registrerà nei prossimi 5 giorni  
* **"umiditaMassimaAssoluta"** è il massimo valore dell'umidità che si registrerà nei prossimi 5 giorni  
* **"mediaUmidità"** è il valore dell' umidità media che si registrerà nei prossimi 5 giorni

# GET /load/{cityName} <a name="8"></a>
Popola un vettore di dati letti da file, con possibilità di caricare dati da una città specifica(Inserire il nome della città al posto di {City}).

//todo//

#### ESEMPIO:
GET localhost:8080/load/Ancona

Su postman restituirà un valore boolean true se il processo è andato a buon fine

# GET /filter/daily/{initialValue}/{finalValue} <a name="9"></a>
Questa rotta va utilizzata **esclusivamente** dopo aver chiamato l'endpoint  <a href = "#7">/load/{cityName}</a><br>
Si creerà un vettore contenente valori salvati precedentemente in un file e verranno filtrati per le specifiche richieste.

I filtri sono stati programmati dinamicamente, in modo da poter aggiungere elementi di filtraggio. Il vettore è settato a static e posizionato nella classe Filters ci permetterà:<br>
* di applicare altri filtri in futuro in base ad altri valori es:filteredVectorTemperature, filteredVectorCountry <br>
* di fare un ulteriore filtraggio degli elementi in comune tra tutti i Vector presenti ad esempio con il metodo equals() 



Il formato dei parametri {initialValue} e {finalValue} è "dd-MM-yyyy HH:mm::ss" a meno che non si modifichi il formato in ParamsValue---

<details>
<summary>MODEL</summary>
<br>

 ```
 {
    "initialDay": 1642032000,
    "finalDay": 1642118400,
    "startTime": 3600,
    "stopTime": 50400,
    "filteredDays": 86400,
    "filteredElements": 26,
    "tempMin": 5.27,
    "tempMax": 18.92,
    "averageTemp": 15.27,
    "realTemperatureVariance": 78.04,
    "city": {
       "lat": 13.1667,
       "lon": 43.2,
       "country": null,
       "cityName": "Provincia di Macerata",
       "id": 3174379
    },
    "perceivedTemperatureVariance": 33.54
 }
```

</details>

  * **"initialDay"** è la data iniziale di filtraggio espressa in UnixTime
  * **"finalDay"** è la data finale di filtraggio espressa in UnixTime
  * **"startTime"** è l'ora iniziale di filtraggio espressa in UnixTime
  * **"stopTime"** è l'ora finale di filtraggio espressa in UnixTime
  * **"filteredDays"** Sono i giorni filtrati in secondi    (86400 =1)
 * **"filteredElements"** La quantità degli elementi filtrati
 * **"tempMin"** è il valore della temperatura minima del periodo filtrato
  **"tempMax"** è il valore della temperatura massima del periodo filtrato
 * **"averageTemp"** è il valore medio della temperatura del periodo filtrato
    **"city"** è il JSONObject che contiene le informazioni riguardanti la città:
  * **"lat"** è la latitudine
  * **"lon"** è la longitudine
  * **"cityName"** è il nome della città
  * **"id"** è l'id della città
  * **"perceivedTemperatureVariance"** è il valore della varianza della temperatura percepita;

# ECCEZIONI: <a name="3"></a>
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
