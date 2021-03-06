package com.company;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    //7 Crea un _Reference Method_i utilitza'l per imprimir totes les persones de la Collection amb foreach
    public static void imprimir(Persona p) {
        System.out.println("***************************");
        System.out.println(p);
        System.out.println("***************************");
    }

    public static void main(String[] args) {
        //1 Definir la variable format per instaciar els objectes del punt 2 (DateTimeFormatter)
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        //2 Crear
        Persona p1 = new Persona("Arya", Persona.Genere.DONA, LocalDate.parse("25/12/2002",format) );
        Persona p2 = new Persona("Tyrion", Persona.Genere.HOME, LocalDate.parse("12/10/1980",format));
        Persona p3 = new Persona("Cersei", Persona.Genere.DONA, LocalDate.parse("10/01/1984",format));
        Persona p4 = new Persona("Eddard", Persona.Genere.HOME, LocalDate.parse("24/04/1974",format));
        Persona p5 = new Persona("Sansa", Persona.Genere.DONA, LocalDate.parse("24/04/1992",format));
        Persona p6 = new Persona("Jaime", Persona.Genere.HOME, LocalDate.parse("24/04/1979",format));
        Persona p7 = new Persona("Khal", Persona.Genere.HOME, LocalDate.parse("10/08/1979",format));
        Persona p8 = new Persona("Daenerys", Persona.Genere.DONA, LocalDate.parse("12/11/1992",format));
        Persona p9 = new Persona("Davos", Persona.Genere.HOME, LocalDate.parse("12/11/1965",format));
        Persona p10 = new Persona("Jon Neu", Persona.Genere.HOME, LocalDate.parse("12/11/1986",format));
        Persona p11 = new Persona("Brienne", Persona.Genere.DONA, LocalDate.parse("12/11/1989",format));

        //3 Crear un ArrayList d'aquestes persones (llistaPersones)
        List<Persona> llistaPersones = new ArrayList<>();
        llistaPersones.add(p1);
        llistaPersones.add(p2);
        llistaPersones.add(p3);
        llistaPersones.add(p4);
        llistaPersones.add(p5);
        llistaPersones.add(p6);
        llistaPersones.add(p7);
        llistaPersones.add(p8);
        llistaPersones.add(p9);
        llistaPersones.add(p10);
        llistaPersones.add(p11);

        //4 Canvia el següent codi per una expressió lambda:
        /*/
        Collections.sort(llistaPersones, new Comparator<Persona>() {
            @Override
            public int compare(Persona o1, Persona o2) {
                if(o1.getNom().charAt(0) >= o2.getNom().charAt(0)) return 1;
                else return -1;
            }
        });
         */
        Collections.sort(llistaPersones, (o1, o2) -> {
            if (o1.getNom().charAt(0) >= o2.getNom().charAt(0)) return 1;
            else return -1;
            });

        //5 Canvia el següent codi per una expressió lambda
        /*/
           for(Persona p: llistaPersones) {
            System.out.println(p);
           }
         */
        llistaPersones.forEach(persona -> System.out.println(persona));

        //6 Transforma la lambda en una classe anònima
        /*/
        //ordenació alfabètica inversa del nom
        llistaPersones.sort((o1,o2) -> o2.getNom().compareTo(o1.getNom()));
         */
        llistaPersones.sort(new Comparator<Persona>() {
            @Override
            public int compare(Persona o1, Persona o2) {
                return o2.getNom().compareTo(o1.getNom());
            }
        });

        //7 Crea un _Reference Method_i utilitza'l per imprimir totes les persones de la Collection amb foreach
        /*/
           //substituir pel foerech amb el reference method
            for(Persona p: llistaPersones) {
                System.out.println(p);
            }
         */
        llistaPersones.forEach(Main::imprimir);

        //8 Crea un Map de Persona on la key és l'edat i el value és quantes perones hi ha amb aquesta edat.
        // Esbrina com fer els puts al map usant aquests mètodes computeIfPresent i putIfAbsent
        Map<Integer, Integer> mapPersones = new HashMap<>();
        for (Persona persona : llistaPersones) {
            if (!mapPersones.containsKey(persona.getAge()))
                mapPersones.put(persona.getAge(),1);

            else mapPersones.put(persona.getAge(),mapPersones.get(persona.getAge() + 1));
        }

   /*     llistaPersones.forEach(persona -> {
            if (!mapPersones.containsKey(persona.getAge()))
                mapPersones.put(persona.getAge(),1);

            else mapPersones.put(persona.getAge(),mapPersones.get(persona.getAge() + 1));
        });*/

        llistaPersones.forEach(persona -> {
            mapPersones.computeIfPresent(persona.getAge(),(k,v) -> v+1);
            mapPersones.putIfAbsent(persona.getAge(),1);
        });

        //9 Fes un recorregut per imprimir clau i valor del map, amb foreach i una lambda o reference method.
        System.out.println("ex9");
        /*
        mapPersones.keySet().stream().forEach(System.out :: println);
        mapPersones.values().stream().forEach(System.out::println);
        */
        mapPersones.forEach((k, v) -> System.out.println("Key : " + k + " Value : " + v));

        //10 Treu un llistat de les persones DONA. Cal fer servir STREAM.
        System.out.println("ex10");
        List<Persona> Dones = llistaPersones.stream().filter(persona -> persona.getGenere().equals(Persona.Genere.DONA)).collect(Collectors.toList());
        System.out.println(Dones);
        //11 Crea un List amb els menors de 25 (stream)
        System.out.println("ex11");
        List<Persona> joves = llistaPersones.stream().filter(persona -> persona.getAge() < 25).collect(Collectors.toList());
        System.out.println(joves);
        //12 Llistat de Persones que tinguin una 'a' o 'A' al seu nom (stream)
        System.out.println("ex12");
        List<Persona> As = llistaPersones.stream().filter(persona -> persona.getNom().toUpperCase().contains("A")).collect(Collectors.toList());
        System.out.println(As);
        //13 Treu un llistat dels 2 HOMES més joves. (stream)
        System.out.println("ex13");
        List<Persona> Maromos = llistaPersones.stream().sorted(Comparator.comparing(Persona::getAge)).filter(persona -> persona.getGenere().equals(Persona.Genere.HOME)).limit(2).collect(Collectors.toList());
        System.out.println(Maromos);
        //14 Esborrar del llistat les persones entre 30 i 40 anys (removeIf)
        System.out.println("ex14");
        List<Persona> adultos = llistaPersones;
        adultos.removeIf(persona -> persona.getAge() >= 30 && persona.getAge() <= 40);
        System.out.println(adultos);
        //15 sumar 2 dias
        llistaPersones.stream().map(persona -> persona.getDataNaixament().plusDays(2));
    }
}
