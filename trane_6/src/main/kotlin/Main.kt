// Необходимо составить программу, которая помогает пользователю составить план поезда.
// После запуска программа спрашивает пользователя - хочет ли он закончить работу, или составить поезд
import kotlin.random.Random
// Основная функция программы
fun main()
{
    println("Добро пожаловать в программу по созданию плана поезда!")

    // Флаг для завершения программы
    var byebyeExit = false

    // Цикл для взаимодействия с пользователем
    while (!byebyeExit)
    {
        println("Выберите действие:")
        println("1. Создать направление")
        println("2. Продать билеты")
        println("3. Сформировать поезд")
        println("4. Отправить поезд")
        println("5. Завершить программу")

        // Чтение ввода пользователя и обработка выбора
        when (readLine())
        {
            "1" -> createPyti()
            "2" -> sellTickets()
            "3" -> createTrain()
            "4" -> pokaTrain()
            "5" -> byebyeExit = true
            else -> println("Некорректный ввод. Пожалуйста, выберите действие из списка.")
        }
    }
}

// Переменные для хранения состояния программы
var pyti: String? = null // при создании переменной она null, после выполнения createDirection становится string
var passengers: Int = 0
var trainPeople: Int = 0
var trainwagons: MutableList<Int> = mutableListOf()
var PeopleWagons: MutableList<Int> = mutableListOf()

// Создание направления (города отправления и прибытия)
fun createPyti()
{
    val cities = listOf("Невинномысск", "Кизляр", "Махачкала", "Санкт-Петербург", "Ростов-на-Дону", "Кисловодск", "Пятигорск", "Сочи", "Краснодар", "Железноводск", "Черкеск", "Владикавказ", "Новороссийск", "Геленджик", "Москва")
    val fromCity = cities.random()
    var toCity = cities.random()
    while (fromCity == toCity) {
        toCity = cities.random()
    }
    pyti = "$fromCity - $toCity"
    println("Направление создано: $pyti")
}

// Продажа билетов (генерация случайного количества пассажиров)
fun sellTickets()
{
    passengers = Random.nextInt(5, 202)
    println("Продано билетов: $passengers")
}

// Формирование поезда (генерация вместимости вагонов и распределение пассажиров)
fun createTrain()
{
    trainwagons.clear()
    PeopleWagons.clear()
    var remainingPassengers = passengers

    // Работает до тех пор пока все пассажиры не будут зассажены в вагоны. С кажым проходом цикла делается один вагон
    while (remainingPassengers > 0) {
        val wagonCapacity = Random.nextInt(1, 26) // рандомно создается вместимость пассажиров в вагоне
        // Распределение пассажиров вагоне, обычно работает первая скобка,
        // вторая нужна для тех случаев когда оставшихся пассажиров меньше, чем вместимость вагона строчкой выше
        val passengersInWagon = if (remainingPassengers >= wagonCapacity) wagonCapacity else remainingPassengers
        PeopleWagons.add(wagonCapacity)// записывает вместимость вагона в mutable list
        trainwagons.add(passengersInWagon)// в mutable list записывается int значение, означающее кол-во пассажиров в вагоне
        remainingPassengers -= passengersInWagon // вычетается из общего кол-во пассажиров, распределеной в этом вагоне
    }

    trainPeople = trainwagons.size

    // Вывод информации о поезде с учетом слова "вагон"
    if ((trainPeople == 1) || (trainPeople == 21))
    {
        println("Поезд сформирован: $trainPeople вагон")
    }
    else if ((trainPeople == 2) || (trainPeople == 3) || (trainPeople == 4)|| (trainPeople == 22)|| (trainPeople== 23)|| (trainPeople == 24)) {
        println("Поезд сформирован: $trainPeople вагона")
    }
    else
        println("Поезд сформирован: $trainPeople вагонов")
}

// Отправка поезда (вывод информации о направлении, количестве вагонов и пассажирах в каждом вагоне)
fun pokaTrain()
{
    // Проверка, что направление и поезд были созданы
    if (pyti != null && trainPeople > 0)
    {
        println("Поезд $pyti, состоящий из $trainPeople вагонов, отправлен.")
        for ((index, capacity) in trainwagons.withIndex())
        {
            println("Вагон ${index + 1}: вместимость ${PeopleWagons[index]}, пассажиров $capacity")
        }
    } else
    {
        println("Направление или поезд не были созданы. Пожалуйста, выполните предыдущие шаги.")
    }
}