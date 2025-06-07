# 1 курс 2 семестр — Системная инженерия: WEB-проект ver.1

## Создаём структуру

```plaintext
MyWebApp/
├── src/              # Java-код (Servlet'ы и т.д.)
├── web/              # HTML, CSS, JSP
│   └── index.jsp     # Стартовая страница
├── WEB-INF/          # Конфигурация приложения
│   └── web.xml
├── lib/              # Сюда положим sqlite.jar
└── README.md

## Настройка основной структуры проекта с сервлетами

```plaintext
MyWebApp/
├── src/                # Java-сервлеты
│   └── auth/           # Папка для регистрации и авторизации
│       ├── LoginServlet.java
│       └── RegisterServlet.java
├── web/                # HTML / JSP
│   ├── index.jsp
│   ├── login.jsp
│   └── register.jsp
├── WEB-INF/
│   └── web.xml         # Тут регистрируем сервлеты
├── lib/
│   ├── sqlite-jdbc.jar # Сюда положим SQLite драйвер
    └── javax.servlet-api-4.0.1.jar


## Меняем структуру, для нормальной компиляции
```plaintext
MyWebApp/
├── src/                    # Исходники Java
│   └── auth/
│       ├── LoginServlet.java
│       └── RegisterServlet.java
├── lib/                    # JAR-библиотеки
│   ├── javax.servlet-api-4.0.1.jar
│   └── sqlite-jdbc.jar
├── build/                  # Куда компилируются .class-файлы
├── web/                    # HTML/JSP + WEB-INF
│   ├── index.jsp
│   ├── login.jsp
│   ├── register.jsp
│   └── WEB-INF/
│       ├── web.xml
│       ├── classes/       # Сюда мы копируем build-результаты 
│       └── lib/           # Сюда пойдут JAR’ы при создании WAR

Копирование .class в WEB-INF/classes/:
xcopy /s /y build\* web\WEB-INF\classes\

Копирование JAR-файлов в WEB-INF/lib/:
xcopy /y lib\*.jar web\WEB-INF\lib\

##Таблица items

| Поле          | Назначение                                                       |
| ------------- | ---------------------------------------------------------------- |
| `id`          | Уникальный идентификатор, `PRIMARY KEY AUTOINCREMENT` — стандарт |
| `name`        | Название объекта — кратко и ясно                                 |
| `description` | Подробное описание объекта                                       |
| `price`       | Цена, если это товары / платные объекты (`REAL` — дробное число) |
| `image`       | Путь до изображения (например, `"img/item1.jpg"`)                |


##Напоминалка для компиляции в терминале в корне проекта
C:\Users\svint\Desktop\vuzik\1kursmaga\2sem\web>
javac -encoding UTF-8 -d build -classpath "lib\sqlite-jdbc.jar;lib\javax.servlet-api-4.0.1.jar" src\auth\*.java

javac -encoding UTF-8 -d build -classpath "lib\sqlite-jdbc.jar;lib\javax.servlet-api-4.0.1.jar" src\catalog\*.java  

После компиляции - xcopy /s /y build\* web\WEB-INF\classes\

Перезапустить Tomcat
cd C:\Users\svint\Desktop\vuzik\apache-tomcat-9.0.105\bin
startup.bat
shutdown.bat
http://localhost:8080/WebApp/register.jsp