Laboratorium III - JPQL

Uwaga! Do wykonania zadan konieczne jest zaimplementowanie architektury warstwowej i testow z Laboratorium II !

Uzupelnij plik data.sql o dane niezbedne do realizacji nastepujacych zapytan:
1. Znajdz pacjentow po nazwisku
2. Znajdz wszystkie wizyty pacjenta po jego ID
3. znajdz pacjentow ktorzy mieli wiecej niz X wizyt (X jest parametrem wejsciowym)
4. Znajdz pacjentow po dodanym przez Ciebie polu - nie wyszukuj wprost po wartosci, uzyj zapytania typu wieksze/mniejsze/pozniej/wczesniej/zawiera, w zaleznosci od wybranego typu zmiennej.

Napisz testy do zapytan w nastepujacej formie:
1. do zapytania nr 1  - test DAO
2. do zapytania nr 2 - test serwisu
3. do zapytania nr 3 - test DAO
4. do zapytania nr 4 - test DAO

W PatientEntity, nad relacja do VisitEntity dodaj adnotacje

@Fetch(FetchMode.SELECT)

a fetchType zmien na EAGER
Uruchom test w ktorym pobierany jest Patient z wieloma wizytami. W logach zaobserwuj, jak wyglada pobieranie dodatkowych encji (ile i jakie sqle).
Nastepnie zmien adnotacje na

@Fetch(FetchMode.JOIN)

i powtorz test i obserwacje. Wnioski zapisz na dole tego pliku i skomituj.

Do wybranej encji dodaj wersjonowanie, oraz napisz test (w DAO) sprawdzajacy rownolegla modyfikacje (OptimisticLock)

WNIOSEK
FetchMode.SELECT vs FetchMode.JOIN
W początkowej konfiguracji relacji PatientEntity → VisitEntity, domyślne ładowanie listy wizyt (visits) 
odbywało się z wykorzystaniem @Fetch(FetchMode.SELECT). 
Oznaczało to, że dla każdego pacjenta Hibernate wykonywał osobne zapytanie do bazy danych w celu pobrania 
jego wizyt. Skutkowało to tzw. problemem N+1 zapytań, co mogło prowadzić do dużego obciążenia bazy
przy większej liczbie pacjentów.

Po zmianie adnotacji na:

@OneToMany(mappedBy = "patient", fetch = FetchType.EAGER)
@Fetch(FetchMode.JOIN) <-
private List<VisitEntity> visits;
Hibernate zamiast wielu SELECTów wykonał jedno zapytanie typu JOIN, w którym od razu pobrał dane pacjenta 
wraz z jego wizytami i lekarzami. Dzięki temu zmniejszyła się liczba zapytań do bazy, dane są dostępne natychmiast (brak opóźnień przy dostępie do listy wizyt),

Wniosek:
Zastosowanie @Fetch(FetchMode.JOIN) pozwala uniknąć problemu N+1 zapytań i poprawia wydajność pobierania danych
w relacjach typu @OneToMany.