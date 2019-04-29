package XMLQuery;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author 90%Erwin & 10%Jorge
 */
public class SimpleQuery 
{
    public String rutaArchivo;
    public String[] atributos;
    public String tipo;
    
    public SimpleQuery(String rutaArchivo)
    {
        this.rutaArchivo = rutaArchivo;
        this.getFileAttribute();
    }
    
    public int getMax(List<Element> lista, int start, int end)
    {
        int j = 0;
        for (int i = start; i < end; i++) 
        {
            int id = Integer.parseInt(lista.get(i).getChildText(atributos[0]));
            if(id > j)
                j = id;
        }
        return j;
    }
    
    public int getMax()
    {
        try
        {
            Document documento = openFileRead();
            Element rootNode = documento.getRootElement();
            List<Element> lista = rootNode.getChildren(tipo);
            return this.getMax(lista, 0, lista.size());
        }
        catch (IOException | JDOMException e) 
        {
            System.out.println(e.getMessage());
        }
        return -1;
    }
    
    private void openFileWrite(Element raiz)throws IOException
    {
        raiz.getParent().removeContent();
        Document doc = new Document(raiz);
        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getPrettyFormat());
        try (FileOutputStream writer = new FileOutputStream(rutaArchivo)) {
            xmlOutput.output(doc, writer);
            writer.flush();
            writer.close();
        }
    }
    
    private Document openFileRead() throws JDOMException, IOException
    {
        SAXBuilder constructor = new SAXBuilder();
        File xml = new File(rutaArchivo);
        return constructor.build(xml);
    }
    
    private void getFileAttribute()
    {
        try
        {
            Document documento = openFileRead();
            Element rootNode = documento.getRootElement();
            List<Element> lista = rootNode.getChildren();
            Element elemento = lista.get(0);
            this.tipo = elemento.getName();
            List<Element> atributos = elemento.getChildren();
            this.atributos = new String[atributos.size()];
            for(int i = 0; i < atributos.size(); i++)
            {
                this.atributos[i] = atributos.get(i).getName();
            }
        }
        catch (IOException | JDOMException io) 
        {
            System.out.println(io.getMessage());
        }
    }
    
    private int binarySearch(List<Element> lista, String identificador, int limInf, int limSup)
    {
        if(limSup < limInf)
                return -1;
        int temp = (limSup + limInf) / 2;
        int elemento = Integer.parseInt(lista.get(temp).getChildText(atributos[0]));
        int condicion = Integer.parseInt(identificador);
        if(elemento > condicion)
            return binarySearch(lista, identificador, limInf, temp - 1);
        else
            if(elemento < condicion)
                return binarySearch(lista, identificador, temp + 1, limSup);
        return temp;
    }
    
    private ArrayList<Integer> linearSearch(List<Element> lista, String atributo, String valor, int start, int end)
    {
        ArrayList<Integer> j = new ArrayList<>();
        for (int i = start; i < end; i++) 
        {
            String id = lista.get(i).getChildText(atributo);
            if(id.equals(valor))
                j.add(i);
        }
        return j;
    }
    
    private ArrayList<Integer> linearSearch(List<Element> lista, int numAtributo, String valor, int start, int end)
    {
        ArrayList<Integer> j = new ArrayList<>();
        for (int i = start; i < end; i++) 
        {
            String id = lista.get(i).getChildText(this.atributos[numAtributo]);
            if(id.equals(valor))
                j.add(i);
        }
        return j;
    }
    
    private HashMap<String,String> convertElementMap(Element elemento)
    {
        List<Element> lista = elemento.getChildren();
        HashMap data = new HashMap();
        for (int i = 0; i < lista.size(); i++) 
            data.put(atributos[i], lista.get(i).getText()) ;
        return data;
    }
    
    /**
     * Obtiene las tuplas que cumplan una condición de tipo atributo = valor
     *
     * @param atributo Atributo que se comparará
     * @param valor Valor a igualar
     * @return Un arreglo de tuplas que cumplen esta condición
     */
    public HashMap<String,String>[] select(String atributo, String valor)
    {
        HashMap[] data = null;
        try
        {
            Document documento = openFileRead();
            Element rootNode = documento.getRootElement();
            List<Element> lista = rootNode.getChildren(tipo);
            ArrayList<Integer> j = this.linearSearch(lista, atributo, valor, 0, lista.size());
            if(j.size() > 0)
            {
                data = new HashMap[j.size()];
                for (int i = 0; i < j.size(); i++)
                    data[i] = this.convertElementMap(lista.get(j.get(i)));
            }
        }
        catch (IOException | JDOMException e) 
        {
            System.out.println(e.getMessage());
        }
        return data;
    }
    
    /**
     * Obtiene todas las tuplas en el archivo
     *
     * 
     * @return Un arreglo de tuplas
     */
    public HashMap<String,String>[] select ()
    {
        HashMap[] data = null;
        try
        {
            Document documento = openFileRead();
            Element rootNode = documento.getRootElement();
            List<Element> lista = rootNode.getChildren(tipo);
            data = new HashMap[lista.size()];
            for (int i = 0; i < lista.size(); i++)
                data[i] = this.convertElementMap(lista.get(i));
        }
        catch (IOException | JDOMException e) 
        {
            System.out.println(e.getMessage());
        }
        return data;
    }
    
    /**
     * Obtiene las tuplas que cumplan que su identificador sea igual al indicado
     *
     * @param identificador Identificador a buscar
     * @return La tupla que cumpla esta condición
     */
    public HashMap<String,String> select(String identificador)
    {
        try
        {
            Document documento = openFileRead();
            Element rootNode = documento.getRootElement();
            List<Element> lista = rootNode.getChildren(tipo);
            int i = this.linearSearch(lista, 0, identificador, 0, lista.size()).get(0);
//            int i = binarySearch(lista, identificador, 0, lista.size());
            if(i != -1)
                return this.convertElementMap(lista.get(i));
            return null;
        }
        catch (IOException | JDOMException e) 
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public boolean insert(HashMap<String,String> informacion)
    {
        if(informacion.size() != atributos.length)
                return false;
        try{
            Document doc = openFileRead();
            Element raiz = doc.getRootElement();
            Element tupla = new Element(this.tipo);
            for(int i = 0; i < informacion.size(); i++)
            {
                Element atributo = new Element(atributos[i]);
                String valor = informacion.get(atributos[i]);
                if(valor == null || valor.equals(""))
                    return false;
                atributo.setText(valor);
                tupla.addContent(atributo);
            }
            raiz.addContent(tupla);
            this.openFileWrite(raiz);
            return true;
	}
	catch (JDOMException | IOException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean update(String identificador, HashMap<String, String> informacion){
        try{
            Document documento = openFileRead();
            Element rootNode = documento.getRootElement();
            List<Element> lista = rootNode.getChildren(tipo);
            int i = this.linearSearch(lista, 0, identificador, 0, lista.size()).get(0);
//            int i = binarySearch(lista, identificador, 0, lista.size());
            if(i == -1)
                return false;
            Element nodo = lista.get(i);
            for (String atributo1 : this.atributos) 
            {
                String valor = informacion.get(atributo1);
                if (valor == null || valor.equals("") || atributo1.equals("identificador") || atributo1.startsWith("id"))
                    continue;
                Element atributo = nodo.getChild(atributo1);
                atributo.setText(valor);
            }
            openFileWrite(rootNode);
            return true;
        }catch(JDOMException | IOException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean delete(String identificador)
    {
        try{
            Document documento = openFileRead();
            Element rootNode = documento.getRootElement();
            List<Element> lista = rootNode.getChildren(tipo);
            int i = this.linearSearch(lista, 0, identificador, 0, lista.size()).get(0);
//            int i = binarySearch(lista, identificador, 0, lista.size());
            if(i == -1)
                return false;
            Element nodo = lista.get(i);
            rootNode.removeContent(nodo);
            openFileWrite(rootNode);
            return true;
        }catch(JDOMException | IOException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}