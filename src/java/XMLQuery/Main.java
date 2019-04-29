package XMLQuery;

import java.util.HashMap;

public class Main 
{
    public static void Main(String[] args)
    {
        SimpleQuery consulta = new SimpleQuery("C:\\Users\\Erwin\\OneDrive\\Documentos\\NetBeansProjects\\Proyecto\\categoria.xml");
        {
            System.out.println("Tipo: " + consulta.tipo);
            HashMap query = consulta.select("1"); //Selecciona la tupla que cumple con que su identificador sea "1"
            for (String atributo : consulta.atributos) 
            {
                System.out.println(atributo + ": " + query.get(atributo));
            }
        }
        System.out.println();
        {
            HashMap[] query2 = consulta.select("idCategoria", "1"); //Selecciona las tuplas 
            //que cumple con que el atributo indicado 
            //por el primer parámetro sea igual al segundo parámetro
            for (HashMap query1 : query2) 
            {
                for (String atributo : consulta.atributos) 
                {
                    System.out.println(atributo + ": " + query1.get(atributo));
                }
            }
        }
        System.out.println();
        {
            HashMap[] query3 = consulta.select(); //Regresa todas las tuplas
            for (HashMap query1 : query3) 
            {
                for (String atributo : consulta.atributos) 
                {
                    System.out.println(atributo + ": " + query1.get(atributo));
                }
            }
        }
//        HashMap<String, String> mapa1 = new HashMap();
//        mapa1.put(consulta.atributos[0], "10");
//        mapa1.put(consulta.atributos[1], "MariaaaLMNk");
//        mapa1.put(consulta.atributos[2], "low");
//        mapa1.put(consulta.atributos[3], "yin fong");
//        mapa1.put(consulta.atributos[4], "fong fong");
//        mapa1.put(consulta.atributos[5], "400000");
//        System.out.println(consulta.insert(mapa1));
//        HashMap<String, String> mapa2 = new HashMap();
//        mapa2.put(consulta.atributos[1], "MariaaaLMNk");
//        mapa2.put(consulta.atributos[2], "low");
//        mapa2.put(consulta.atributos[3], "yin fong");
//        mapa2.put(consulta.atributos[4], "fong fong");
//        mapa2.put(consulta.atributos[5], "400000");
//        System.out.println(consulta.update("2", mapa2));
    }
}
