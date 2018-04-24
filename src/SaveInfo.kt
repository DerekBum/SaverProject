import java.io.File
import java.io.InputStream
import java.io.PrintWriter
import java.util.*

val a = HashMap<String,String>() // creating HashMap
fun main(args: Array<String>) {
    reade()
    var ist = true
    println("print 'help' to get help")
    while (ist){
        print(">>  ")
        val string = readLine()
        if (string == null ){
            println("print correct command")
        }

        else{
            val list = string.split(" ")
            try {
            when(list[0]){
                "help" -> getHelp()
                "quit" -> ist = false
                "add" -> add(list)
                "find" -> find(list)
                "delete" -> delete(list)
                else -> println("print correct command. (print 'help' to get help)")
            }}
            catch (e:Exception){
                println("print correct command. (print 'help' to get help)")
            }

        }
        write()


    }


}


fun getHelp(){
    println("'add key:string' - adds a string by key" + '\n'+
            "'add -mn n' - adds n strings with keys (key:string)" + '\n'+
    "'find -a' - returns all strings" + '\n'+
    "'find key:string' - finds string by key and/or string. You can 'find *:string' or 'find key:*'"+ '\n'+
    "'find -i key:string' - finds string by key and/or string with not completed string/key " + '\n'+
    "'delete -a' - deletes all strings"+'\n'+
    "'delete key:string' - deletes string by key and/or string. You can 'delete *:string' or 'delete key:*'" + '\n'+
    "'delete -i key:string' - deletes string by key and/or string with not completed string/key " + '\n'+
    "'quit' - stop the program")

}

fun add(list: List<String>){

    if (list[1]=="-mn"){
        for (i in 1..list[2].toInt()){
            print(">>  ")
            val string = readLine()
            a.put(string.toString().split(":")[0],string.toString().split(":")[1])
        }
    }
    else{
        val string = list[2]
        a.put(string.split(":")[0],string.split(":")[1])
    }
    print("<<  ")
    println("added")
}

fun find(list: List<String>){
    if (list[1]== "-a"){
        for (i in a){
            print("<<  ")
            val list1 = i.toString().split("=")
            println(list1[0]+" : "+ list1[1])
        }
    }
    else if(list[1] == "-i"){
        val list2 = list[2].split(":")
        for (i in a){
            val list1 = i.toString().split("=")
            if (((list2[0] in list1[0])and (list2[0]!="*")) or ((list2[1] in list1[1]) and (list2[1]!="*"))){
                print("<<  ")
                println(list1[0]+" : "+ list1[1])
        }}
    }

    else {
        val list2 = list[1].split(":")
        for (i in a){

            val list1 = i.toString().split("=")
            if (((list2[0] == list1[0])and (list2[0]!="*")) or ((list2[1] == list1[1]) and(list2[1]!="*"))){
                print("<<  ")
                println(list1[0]+" : "+ list1[1])
        }}
    }

}


fun delete(list: List<String>){
    if (list[1]== "-a"){
        a.clear()
    }
    else if(list[1] == "-i"){
        val list2 = list[2].split(":")
        for (i in a){
            val list1 = i.toString().split("=")
            if (((list2[0] in list1[0])and (list2[0]!="*")) or (list2[1] in list1[1])){
                a.remove(list1[0])
                return
        }}
        println("<< Not found")
    }

    else {
        val list2 = list[1].split(":")
        for (i in a){
            val list1 = i.toString().split("=")
            if (((list2[0] == list1[0])and (list2[0]!="*")) or (list2[1] == list1[1])){

                a.remove(list1[0])
                return

        }}

        println("<< Not found")
    }


}



fun write() {
    val writer = PrintWriter("strings.txt")

    for (i in a){
        writer.append(i.toString()+'\n')
    }



    writer.close()
}


fun reade(){
    val inputStream: InputStream = File("strings.txt").inputStream()
    val lineList = mutableListOf<String>()


    inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it)} }

    for(i in lineList){
        val list = i.split("=")
        a.put(list[0],list[1])
    }
}