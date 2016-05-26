def sum(x: Int, y: Int, z: Int): Int = x + y + z

def sebas = 1 + 1


val sumCarried: (Int) => (Int) => (Int) => Int = (sum _).curried

sumCarried(1)(2)(3)

val plus1 = sumCarried(1)

plus1(2)(2)

val plus1and3 = sumCarried(1)(3)

plus1and3(2)


// Block

case class User(name: String)
val user = User("Juan")

def userLogger(user: User)(block: User => Unit) = {
  println(user)
  block(user)
}

def userLogger(request: Int)(block: User => Unit) = {
  println(user)
  block(user)
}

userLogger(user){user =>
  val a = 1
  println("hola")
}

userLogger(1){user =>
  val a = 1
  println("hola")
}

// default parameters



// type Inference
def concat[A](x: A, y: A)(f: (A,A) => A) = f(x,y)

def commaSep(x: String, y: String) = concat(x,y)((a,b) => a + b)

commaSep("Juan", "Her")

// Varargs

def printSebas(xs: String *)(yx: Int *) = {
  xs.foreach(println)
  yx.foreach(println)
}

printSebas("1", "sebas")(1,2,3)
