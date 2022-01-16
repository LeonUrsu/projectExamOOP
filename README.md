# Proggetto OOP 
Lo scopo di questo progetto è di sviluppare un applicazione SpringBoot web che data una città cercata a piacimento dall'utente tramite l'utilizzo di due API. Con l'uso della prima [5 days weather forecast](https://openweathermap.org/forecast5#name5) si visualizzino tutte le informazioni attuali e future relative all'umidità dei successivi 5 giorni ogni 3 ore e con la seconda [current weather data](https://openweathermap.org/current) si salvino ogni ora le informazioni relative all'umidità e le temperature(massime, minime, reali e percepite).

L'utente grazie a [Postman](https://learning.postman.com/docs/getting-started/introduction/), puo utilizzare le funzionalità dell'applicazione [Spring](https://docs.spring.io/spring-framework/docs/current/reference/html/) che con la sua dipendenza "spring-boot-starter-web" riesce ad avviare facilmente un server Tomcat embedded sulla porta 8080.  

#### INDICE
<p>
     • <a href="#1">L'Applicazione</a><br>
     • <a href="#10">PLUS</a><br>
     • <a href="#2">Rotte Disponibili</a><br>
     • <a href="#3">Eccezioni</a><br> 
</p>

# L'applicazione <a name="1"></a>
Tutti i valori delle temperature restituiti dalla chiamata all'API sono impostati in gradi centigradi (°C)<br>
L'applicazione è strutturata in modo che dopo la call all'api **5 days weather forecast** prenda i valori restituiti da essa e li salvi su un vettore statico(in modo che siano accessibili anche dall'endpoint <a href="#7">/getHumidityStats </a>).

Invece per quanto riguarda la call all'api **current weather data** , la risposta viene salvata in stringa su file in formato json tramite l'utilizzo di un vettore statico ogni ora ,grazie all'utilizzo di un timer. All'avvio dell'endpoint /startCurrentService l'applicazione verificherà se ci siano o meno dei valori nel file, se già esistenti li caricherà in un vettore. In caso ci siano gia valori nel file,l'applicazione verificherà che l'id della città già salvata in precedenza sia lo stesso della città passata come parametro a Postman.Dopodichè si creerà un javaObject contenente i valori correnti delle previsioni(restituiti dall'api) e si salveranno all'interno del vettore. Ora avremo un vettore popolato da javaObject che verrà salvato su un file in formato JSON(grazie all'utilizzo di un metodo che farà il parsing degli elementi del vettore da JavaObject a JSON). 

### PLUS <a name="10"></a>
L'applicazione oltre alle funzioni richieste:<br>
•E' possibile avviare e fermare la rotta del salvataggio dei dati su file, in modo tale da salvare città diverse senza dover fermare il programma, i file verranno salvati nell cartella temp con sintassi "CurrentForecastData{cityName}" ("{cityName}" è variabile a seconda della città richiesta).<br>
•E' possibile modificare dei parametri in [ParamVariable](src/main/java/it/uni/main/utils/ParamVariable.java) dall'utilizzatore dell' applicazione. <br>
•I filtri sono stati programmati dinamicamente, in modo da poter aggiungere elementi di filtraggio. Il vettore è settato a static e posizionato nella classe Filters ci permetterà: di applicare altri filtri in futuro in base ad altri valori (es:filteredVectorTemperature, filteredVectorCountry) e di fare un ulteriore filtraggio degli elementi in comune tra tutti i Vector presenti ad esempio con il metodo equals() <br>
• Per realizzare questo progetto abbiamo utilizzato quasi tutti gli argomenti del corso.


### Come funziona: ROTTE DISPONIBILI <a name="2"></a>

| Rotta         |    Metodo    |        Funzione                        |
|---------------|--------------|----------------------------------------|
| <a href="#4">/getCompleteForecast</a>   | GET        | Avvia ricerca e mostra dati per i prossimi 5 giorni ogni 3 ore |
| <a href="#7">/getHumidityStats </a>  | GET          | Crea statistiche relative all'umidità con i dati già presenti   |
| <a href="#5">/startCurrentService</a> | GET      | Avvia il salvataggio dei dati correnti ogni ora     |
| <a href="#6">/stopCurrentService </a>   | GET          | Ferma il salvataggio dei dati correnti        |
| <a href="#8">/load/{cityName}</a> | GET  | Carica su un vettore i dati salvati in precedenza a seconda della città passata|
| <a href="#9">/filter/{initialValueInDay}/{finalValueInDay}/{initialValue}/{finalValue} </a>    | GET          | Filtra le statistiche dei dati salvati ogni ora giornalmente|
| <a href ="#11">/loadFilterTest</a> | GET | Carica su un vettore i dati test salvati in precedenza | 


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
    *  **"dtString"** è la data e l'ora del giorno della previsione espresso in UTC

# GET /startCurrentService <a name="5"></a>
Salva localmente in un file ".json" la risposta di Postman ogni ora grazie ad un timer. Definito l'intervallo del timer, quando la rotta verrà chiamata creerà un JavaObject che verrà passato ad un vettore statico. Quindi tutti i valori verranno memorizzati nella memoria RAM . Successivamente verrà convertito in JSONArray contenente tutti i JSONobject relativi alle previsioni del meteo salvati quando l'intervallo terminerà.

Il timer è impostato di di default ad un ora, è possibile modificarlo a piacimento da [ParamVariable](src/main/java/it/uni/main/utils/ParamVariable.java)<br>
**N.B.** L'API fornisce valori sulle previsioni ogni ora, quindi impostando un valore minore di un ora si otterranno risultati identici.<br>
La massima dimensione del vettore di default è 120, è possibile modificarla a piacimento da [ParamVariable](src/main/java/it/uni/main/utils/ParamVariable.java)

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
 * **""dtString** è la data e ora della previsione espressa in UTC
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
  * **"country"** è lo stato della città
  * **"id"** è l'id della città
* **"umiditaMinimaAssoluta"** è il minor valore dell'umidità che si registrerà nei prossimi 5 giorni  
* **"umiditaMassimaAssoluta"** è il massimo valore dell'umidità che si registrerà nei prossimi 5 giorni  
* **"mediaUmidità"** è il valore dell' umidità media che si registrerà nei prossimi 5 giorni

# GET /load/{cityName} <a name="8"></a>
Popola un vettore di dati letti da file, con possibilità di caricare dati da una città specifica(Inserire il nome della città al posto di {cityName}).

#### ESEMPIO:
GET localhost:8080/load/Ancona

Su postman restituirà un valore boolean true se il processo è andato a buon fine

# GET /loadFilterTest <a name ="11"></a>

Popola un vettore di dati letti da file già salvati dagli sviluppatori con la sola finalità di testare il funzionamento dei filtri.
Oltre a caricare il vettore genererà valori random per temperature e cambiarà il valore dell'orario(il primo orario salvato sarà l'ora attuale e ,con un ciclo for,a tutti gli altri elementi verrà sottratta un ora).
Il file è composto da 120 elementi, quindi si potranno filtrare i valori dal momento della chiamata all'endpoint fino a 5 giorni prima. 

## GET /filter/{initialValueInDay}/{finalValueInDay}/{initialValue}/{finalValue} <a name="9"></a>
Questa rotta va utilizzata **esclusivamente** dopo aver chiamato l'endpoint  <a href = "#7">/load/{cityName}</a> oppure <a href ="#11">/loadFilterTest </a><br>
Si creerà un vettore contenente valori salvati precedentemente in un file e verranno filtrati per le specifiche richieste.

I filtri sono stati programmati dinamicamente, in modo da poter aggiungere elementi di filtraggio. Il vettore è settato a static e posizionato nella classe Filters ci permetterà:<br>
* di applicare altri filtri in futuro in base ad altri valori es:filteredVectorTemperature, filteredVectorCountry <br>
* di fare un ulteriore filtraggio degli elementi in comune tra tutti i Vector presenti ad esempio con il metodo equals()<br>

Il formato dei parametri **{initialValue}** e **{finalValue}** è **"HH:mm:ss"** e il formato dei parametri **{initialValueInDay}** e **{finalValueInDay}** è **"dd-MM-yyyy"** a meno che non si modifichi il formato in FormatHour e FormatDate nella classe [ParamVariable](src/main/java/it/uni/main/utils/ParamVariable.java) .<br>

I valori restituiti sono riferiti alla fascia oraria dei giorni cercati.<br>

**ESEMPIO** <br>
localhost:8080/filter/12-01-2022/15-01-2022/05:00:00/15:50:00<br>
questa chiamatà restituirà i valori nelle fasce orarie tra le 05:00:00 e le 15:50:00 dei giorni 12-13-14-15 Gennaio 2022

<details>
<summary>MODEL</summary>
<br>

 ```
{
    "initialDayInUTC": "12-01-2022 05:00:00",
    "finalDayInUTC": "15-01-2022 15:50:00",
    "initialValueInUnix": 18000,
    "finalValueInUnix": 57000,
    "initialValueDayInUnix": 1641945600,
    "finalValueInDayInUnix": 1642204800,
    "filteredElements": 44,
    "temperatureMin": 4.34,
    "temperatureMax": 18.86,
    "averageTemperature": 16.11,
    "perceivedTemperatureVariance": 21.97,
    "realTemperatureVariance": 82.65,
    "city": {
        "lat": 13.1667,
        "lon": 43.2,
        "country": null,
        "cityName": "Provincia di Macerata",
        "id": 3174379
    }
}
```

</details>

  * **"initialDayInUTC"** sono la data e l'ora iniziale di filtraggio espressa in UTC
  * **"finalDayInUTC"** sono la data e l'ora finale di filtraggio espressa in UTC
  * **"initialValueInUnix"** è l'ora iniziale di filtraggio della fascia oraria espressa in UnixTime
  * **"finalValueInUnix"** è l'ora finale di filtraggio della fascia oraria espressa in UnixTime
 * **"initialValueDayInUnix"** è la data finale di filtraggio della fascia oraria espressa in UnixTime
 * **"finalValueInDayInUnix"** è la data finale di filtraggio della fascia oraria espressa in UnixTime 
 * **"filteredElements"** La quantità degli elementi filtrati
 * **"temperatureMin"** è il valore della temperatura minima del periodo filtrato
  **"temperatureMax"** è il valore della temperatura massima del periodo filtrato
 * **"averageTemp"** è il valore medio della temperatura del periodo filtrato
 * **"perceivedTemperatureVariance"** è il valore della varianza della temperatura percepita
 * **"realTemperatureVariance"** è il valore della varianza della temperatura reale
    **"city"** è il JSONObject che contiene le informazioni riguardanti la città:
  * **"lat"** è la latitudine
  * **"lon"** è la longitudine
  * **"cityName"** è il nome della città
  * **"id"** è l'id della città
 

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

## AUTORI 
•PERAZZOLI LEONARDO<br>
•URSU LEON
