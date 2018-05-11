# Typeclasses

### in Scala

---

### What is a typeclass

- Interface that defines some behaviour
- First introduced in Haskell
- A pattern in Scala not a feature

---

#### Examples in Predef
### Numeric

- Supports + - *
- Supports conversions between number types
- Defines zero and one

---

### Mean
#### opertates on all numbers

```
import Numeric.Implicits._

def mean[A](xs: List[A])(implicit n: Numeric[A]) =
  xs.foldLeft(n.zero)(_ + _).toDouble() / xs.length
```

---

#### Examples in Predef
### Ordering

- Support compare
- Defines < > <= >=
- Defines equivalence

---

### Insertion sort
#### operates on all instances of Ordering

```
import Ordering.Implicits._

def sort[A](xs: List[A])(implicit o: Ordering[A]) =
  xs.foldLeft(List[A]()) { (acc, value) =>
    val (front, back) = acc.span(_ < value)
    front ::: v :: back
  }
```

---

### Why choose a typeclass

- Ad-hoc polymorphism
- Decouples code
- Extension of third party code
- Polymorphic parameters and return types
- Automatic derivation

---

### Polymorphism

- Overloading (Runtime)
- Pattern matching (Runtime)
- Parametric polymorphism
- Subtype polymorphism
- Ad-hoc polymorphism

---

### Parts of a typeclass
- Typeclass (trait)
- Type parameter (generic type)
- Typeclass instances (implicit values)
- Polymorphic operations (implicit parameters / context bounds)

---

## Code

---

### Implicit resolution

1. Explicit
2. Local scope
3. Outer scope
4. Inherited
5. Imported
6. In package object
7. Companion object of typeclass
7. Companion object of T
8. Supertype of companion objects

---

## Code

---

### Downsides
- Use of implicits
- Compile time performance
- Not as performant as inheritance
- Boilerplate
- Ambigious implicits compile errors

---

### Other implementations
- Simulacrum
- Scalaz 8
- Dotty/Scala 3

---

## Questions
