#fibonacci recursive and non recursive
def i_fibonacci(n):
    if n == 0:
        return
    elif n == 1:
        print(0)
        return
    a=0
    b=1
    print(a,b,end=" ")
    for i in range(2,n):
        c=a+b
        a=b
        b=c
        print(c,end=" ")

def r_fibonacci(n):
    if(n<=1):
        return n
    return r_fibonacci(n-1)+r_fibonacci(n-2)

while True:
    n=int(input("Enter the  number till which you want to print the series: "))
    print("The iterative fibonacci series is: ")
    i_fibonacci(n)

    print("\nThe nth number of fibonacci series is: ")
    print(r_fibonacci(n-1))