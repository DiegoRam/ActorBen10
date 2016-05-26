//class constructors
class A {
  val name: String = "Ben"
  def lastname: String = name + "Ram"
}

case class A1(name:String, lastname: () => String)

def func1(x: Int, y: => String) = {
  def go(): String = y
  A1("Hernan", y _)
  def y1 = y
  x + go
}

val a = new A
val a1 = A1("Juan")



//val and def
//expression vs functions (akka methods)
//call-by-value vs anonymous function


