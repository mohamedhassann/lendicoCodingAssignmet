# Assignment for Java Backend Developer (f/m) at Lendico

## General info

1. The task is implemented using Java as programming language and [Spring boot] (http://spring.io/projects/spring-boot) as back-end framework.
2. I used [IntelliJ IDEA](https://www.jetbrains.com/idea/) as IDE.

## Installation and setup

1. Clone this repository
2. Navigate to the cloned project in your computer
    ```
    cd <your_git_home>/Assignment-loanPlanGenerator
    ```
3. Start the spring boot application either from the IDE or by running the following command from terminal
    ```
    mvn spring-boot:run
    ```
4. The application will be up and running on the following URL:
    ```
    localhost:8080/generate-plan
    ```
## Usage

1. By using your favorite HTTP client **I recommend using POSTMAN**, make a POST request to the above URL
2. JSON payload will be something like that
    ```
    {
    	"loanAmount":"5000",
    	"nominalRate":"5",
    	"duration":"24",
    	"startDate":"2018-01-01T00:00:01Z"
    }
    ```
3. The output will be as following JSON
    ```
    {
       [
        {
            "borrowerPaymentAmount": 219.36,
            "date": "2018-01-01T02:00:01Z",
            "initialOutstandingPrincipal": 5000,
            "interest": 20.83,
            "principal": 198.53,
            "remainingOutstandingPrincipal": 4801.47
        },
        {
            "borrowerPaymentAmount": 219.36,
            "date": "2018-02-01T02:00:01Z",
            "initialOutstandingPrincipal": 4801.47,
            "interest": 20.01,
            "principal": 199.35,
            "remainingOutstandingPrincipal": 4602.12
        },
        {
            "borrowerPaymentAmount": 219.36,
            "date": "2018-03-01T02:00:01Z",
            "initialOutstandingPrincipal": 4602.12,
            "interest": 19.18,
            "principal": 200.18,
            "remainingOutstandingPrincipal": 4401.94
        },
        ....
         {
            "borrowerPaymentAmount": 219.28,
            "date": "2019-12-01T02:00:01Z",
            "initialOutstandingPrincipal": 218.37,
            "interest": 0.91,
            "principal": 218.37,
            "remainingOutstandingPrincipal": 0
        }
       ]
    }
    ```

## Author
[Mohamed M.Hassan](mailto:mohamedmhassann@gmail.com)

## License
[MIT](https://choosealicense.com/licenses/mit/)