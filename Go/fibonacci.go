package main

func Fib(n int, z chan<- int) {
	if (n <= 1) {
		z <- n
		return
	}

	c1l := make(chan int)
	c2l := make(chan int)

	go Fib(n-1, c1l)
	go Fib(n-2, c2l)

	x := <-c1l
	y := <-c2l
	z <- (x + y)
}

func main() {
	c0l := make(chan int)
	go Fib(10, c0l)
	u := <-c0l
	// fmt.Println(u)
}