# Unlimited Windows
## Serwer aplikacji wspomogającej procesy logistyczne

## Instalacja

Aby uruchomić aplikacje należy posiadać Jave w wersji 11, silnik bazodanowy PostgreSQL.
Baza danych musi posiadać schema "unlimitedWindows", a dane autentykacyjne powinny wyglądać następująco:
|  |  |
| ------ | ------ |
| Login | postgres |
| Hasło | admin |

Dane można zmienić w pliku application.properties zmieniając wartości w polach:
- spring.datasource.username
- spring.datasource.password

Dodatkowo baza danych musi posiadać sekwencje, do ich wygenerowanie należy wykorzystać następujące polecenie bazodanowe:
```sh
```sh
CREATE SEQUENCE public.seq_window_10
	INCREMENT BY 10
	MINVALUE 200
	MAXVALUE 9223372036854775807
	START 200
	CACHE 1
	NO CYCLE;
	
CREATE SEQUENCE public.seq_window_size_15
	INCREMENT BY 15
	MINVALUE 100
	MAXVALUE 9223372036854775807
	START 100
	CACHE 1
	NO CYCLE;
	
CREATE SEQUENCE public.seq_transport_60
	INCREMENT BY 60
	MINVALUE 500
	MAXVALUE 9223372036854775807
	START 500
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE public.seq_production_25
	INCREMENT BY 25
	MINVALUE 100
	MAXVALUE 9223372036854775807
	START 100
	CACHE 1
	NO CYCLE;
	
CREATE SEQUENCE public.seq_packing_30
	INCREMENT BY 30
	MINVALUE 200
	MAXVALUE 9223372036854775807
	START 200
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE public.seq_order_20
	INCREMENT BY 5
	MINVALUE 100
	MAXVALUE 9223372036854775807
	START 100
	CACHE 1# Unlimited Windows
```
Przed pierwszym uruchomieniem nalezy zbudować aplikację za pomocą narzedzie Maven. Do tego celu należy wykonać polecenie:
```sh
mvn clean install
```

Uruchomić aplikacje należy za pomocą następującej komendy Maven:

```sh
mvn spring-boot:run
```

Aplikacja powinna zostać uruchomiena na porcie 8080

Po pierwszym uruchomieniu aplikacji należy stworzyć użytkownika, można to zrobić wykorzystując program postman.
Należy wysłaś żadanie POST HTTP pod adres localhost:8080/api/authentication/register z poniższym body

```sh
{
    "login": "Admin",
    "password": "admin123"
}
```

Drugą możliwością stworzenia użytkownika do uruchomienie poniższego skryptu curl:

```shell
curl --location --request POST 'localhost:8080/api/authentication/register' \
--header 'Content-Type: application/json' \
--data-raw '{
    "login": "Admin",
    "password": "admin123"
}'
```
## Autor

Mariusz Kaczmarek
