# Strongly-Connected-Components-Visualizer
Проект для летней учебной практики: реализация, визуализация алгоритма Косарайю и Шарира для нахождения конмпонент сильной связности направленного графа. 

Программа должна визуализировать пошаговое выполнение алгоритма Kosaraju & Sharir. Алгоритм находит все компоненты сильной связанности в графе и раскрашивает их в разные цвета. 

## Спецификация

### Требования к вводу исходных данных
Входные данные могут быть заданы тремя способами:
1. Загружены из файла формата json. Граф хранится в виде:
	
	{"<номер изходящей вершины>":[<номер входящей вершины>,...], ...,

 	"<номер вершины>":[x,y],...,
	
	"NodeNumber":<количество вершин>}.
		
	После загрузки воссоздается программой графическое представление графа.

1. Сгенерированы самой программой. Общие данные о графе задаются пользователем в отдельном окне (количество вершин и ребер в графе через пробел), остальное генерируется случайным образом и визуализируются программой. Полученный граф должен быть ориентированным.
   
### Требования к визуализации
Графический интерфейс должен содержать панель управления и холст, на котором будет представлена работа алгоритма.
На панели управления расположены несколько кнопок:
1. Кнопка “Save” – сохраняет построенный граф в файл json.
1. Кнопка “Load”  - загружает граф из файла и строит его на холсте.
1. Кнопка “Generate” – очищает холст, запускает генерацию графа и добавляет граф на холст.
1. Кнопка “Algorithm” – показывает результат выполгения алгоритма для нахождения компонент сильной связности графа, выделяя их отличающимися цветами.
1. Кнопка “Delete” – включает режим удаления вершин и ребер с холста при нажатии на них.
2. Кнопка “Move” – включает режим перемещения вершин графа на хосте, перемещая их при выборе и нажатии на свободное место холста.
1. Кнопка “Clear” – очищает холст.
1. Кнопка "Step by step" - запускает пошаговую реацию алгоритма с указанием скорости выполнения в сплывающем окне.

Также для генерации графа должны быть создано окно, в котором пользователь может указать количество узлов и ребер через пробел, опираясь на которые программа сгенерирует граф.

После каждой итерации алгоритма должен отображаться текст, указывающий на изменения в графе ("DFS первоначальный: рассмотрена вершина №x", "Определение компаненкт СС: раскрашена вершина №y"). О выполнении основных этапов алгоритма сигнализируется сообщением:
1) "Завершен DFS по графу"
2) "Завершено инвертирование графа"
3) "Завершен DFS по инвертированному графу"
4) "Завершено раскрашивание графа по компанентам"

Просмотренные вершины и пройденные ребра в первом этапе алгоритма будут окрашиваться в серый цвет. Далее граф инвертируется и ребра меняют свое направление. В конечном шаге цвет вершины будет зависить от компонент сильной связности, ребра в графе Герца должны выделяться жирным шрифтом. Скорость визуализации итераций алгоритма должна быть задана автоматически (т.е. будет фиксированной).

Диаграмма сценариев представлена на рисунке 1, макет интерфейса – на рисунке 2. 	

 <img src="https://github.com/diss03/Strongly-Connected-Components/assets/90706633/589cb509-4c2c-49ca-bb35-6b48583d4638" width="600px" align="center">
 
Рис. 1 – Диаграмма сценариев использования приложения

 <img src="https://github.com/diss03/Strongly-Connected-Components/assets/90706633/ba31451e-64bc-4e70-9aa5-27bc398acb24" width="600px" align="center">
 
Рис. 2 – Макет графического интерфейса приложения
  
### Псевдокод алгоритма
    
	// Инициализация (создание пустого списка)
	L = {}
	
	// Серия поисков в глубину графа G
	while существуют не просмотренные вершины графа do:
		U = первая не просмотренная вершина
		Поиск в глубину: DFS(u)
	
	// Создаем список вершин в порядке увеличения времени выхода Tout
		L = вершины в порядке увеличения времени выхода Tout
	end while
	
	Строим транспонированный граф GT.
	Инвертируем список L (в порядке уменьшения времени выхода Tout)
	
	// Серия поисков в глубину графа GT  в порядке уменьшения времени выхода
	for each (u принадлежит L) и (u – не просмотрена) do:
		Поиск в глубину: DFS(u)
		Очередная компонента сильной связности = множество просмотренных на данном этапе вершин.
	end for


## План разработки и распределение ролей в бригаде

### План разработки

03.07 – Начало работы с отчетом, написание раздела спецификаций, описание ролей участников команды. Создание диаграммы состояний для описания процесса пошагового исполнения алгоритма. Согласовние спецификации. Создание Maven-проекта.
	
05.07. – Создание прототипа приложения: реализация структур данных и алгоритма, тестов для структур данных и алгоритма, набросков интерфейса. Создание диаграмм классов и описание сущностей. Описание тестовых случаев.
	
07.07 – Создание первой версии приложения: реализация генерации данных, выполение и отображение результата работы алгоритма. Описание интерфейса взаимодействия с выполнением алгоритма.
	
10.07 – Создание второй версии приложения: реализация корректной работы кнопоки, отвечающей за пошаговое выполнение алгоритма, и кнопки, отвечающей за удаление объектов с холста; реализация структур данных, отвечающих за пошаговое выполнние алгоритма; реализация тестов для этих структур. Оптимизация кода.
	
12.07 – Сборка проекта в jar-архив, предоставление итогового отчета.

### Распределение ролей в бригаде

Степаненко Денис – покрытие программ тестами, связь интерфейса и внутренних структур данных.
	
Возмитель Влас – визуализация, создание интерфейса программы.
	
Тарасов Константин -  реализация алгоритма и структур данных.

