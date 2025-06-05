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
│   └── sqlite-jdbc.jar # Сюда положим SQLite драйвер