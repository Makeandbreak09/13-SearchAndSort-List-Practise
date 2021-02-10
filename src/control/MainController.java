package control;

import model.List;
import model.Person;

public class MainController {

    private List<Person> allPersons;
    private String[] names = {"Alsbach", "Bachmann", "Cyrus", "Davidoff", "Eregon", "Füller","Giesehau","Halidsch","Irimoff","Zylla","Yilderim","Lupp","Schein","Xenomo","Iwan","Ali","Kötter","Kleinhans","Diablo","Overwatch","Starcraft","Warcraft","Minecraft","Krunker","Command","And","Conquer","Path","Exile","Valheim"};

    public MainController(int amount){
        allPersons = new List<>();
        for(int i = 0; i < amount; i++){
            allPersons.append(createPerson());
        }
    }

    private Person createPerson(){
        Person p = new Person(getRandomName());
        return p;
    }

    public String getRandomName(){
        return names[(int)(Math.random()*names.length)];
    }

    public String showList(){
        String output = "Ausgabe: ";
        allPersons.toFirst();
        while(allPersons.hasAccess()){
            output = output + allPersons.getContent().getName()+" ("+allPersons.getContent().getBirthdate()+"), ";
            allPersons.next();
        }
        return output;
    }

    /**
     * Schreibe einen Algorithmus zum Suchen einer Person innerhalb einer Liste. Falls die Person gefunden wurde, gib ihren Namen samt Geburtsdatum aus.
     * @param name
     * @return
     */
    public String searchList(String name){
        String output = "Nicht gefunden.";
        //TODO 01: Schreibe einen Suchalgorithmus
        allPersons.toFirst();
        while(allPersons.hasAccess() && !allPersons.getContent().getName().equals(name)){
            allPersons.next();
        }
        if(allPersons.hasAccess()){
            output = allPersons.getContent().getName()+" ("+allPersons.getContent().getBirthdate()+")";
        }
        return output;
    }

    /**
     * Stortiere die Liste nach Namen (nicht nach Geburtsdatum!). Entscheide dich hierzu für einen Sortieralgorithmus.
     * Gib an, ob deine Umsetzung stabil ist und ob sie in-place ist.
     */
    public void sortList(){
        //TODO 02: Schreibe einen Sortieralgorithmus
        int length=0;
        allPersons.toFirst();
        while (allPersons.hasAccess()){
            allPersons.next();
            length++;
        }
        quickSort(allPersons, 0, length-1);
    }

    private void quickSort(List<Person> list, int start, int end){
        int i = start;
        int j = end;
        int middle = (start+end)/2;
        list.toFirst();
        for (int k = 0; k<middle; k++){
            list.next();
        }
        Person pivot = list.getContent();
        Person tempP1, tempP2;

        while(i<=j) {
            list.toFirst();
            for (int k = 0; k < i; k++) {
                list.next();
            }
            while (list.getContent().getName().compareTo(pivot.getName()) < 0) {
                list.next();
                i++;

            }
            tempP1 = list.getContent();

            list.toFirst();
            for (int k = 0; k < j; k++) {
                list.next();
            }
            while (list.getContent().getName().compareTo(pivot.getName()) > 0) {
                j--;
                list.toFirst();
                for (int k=0; k<j; k++){
                    list.next();
                }
            }
            tempP2 = list.getContent();

            if(i<=j) {
                list.toFirst();
                for (int k = 0; k < i; k++) {
                    list.next();
                }
                list.setContent(tempP2);

                list.toFirst();
                for (int k = 0; k < j; k++) {
                    list.next();
                }
                list.setContent(tempP1);

                i++;
                j--;
            }
        }

        if(start<j){
            quickSort(list, start, j);
        }
        if(i<end){
            quickSort(list, i, end);
        }

    }

}
