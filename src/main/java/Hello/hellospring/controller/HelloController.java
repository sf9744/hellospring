package Hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//Controller는 주로 사용자의 요청을 처리하고 난 후 정해진 뷰에 객체를 넘겨주는 역할을 한다.
@Controller
public class HelloController {

    //http://localhost:8080/hello
    @GetMapping("hello")
    public String hello(Model model) {
        //addAttribute() 메소드를 이용하여 View에 전달할 데이터를 key, value 형태로 model에 저장한다.
        model.addAttribute("data", "hello!");
        //hello를 반환하면 viewResolver가 해당 템플릿을 찾고 thymeleaf에 넘긴다.
        return "hello";
    }
    //thymeleaf는 model에 저장돤 데이터를 가져와 값을 변환하고 변환된 HTML을 웹 브라우저에 반환한다.

    @GetMapping("hello-mvc")
    public String hellowMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    //API 방식 – 데이터를 그대로 넘긴다.
    @GetMapping("hello-string")
    @ResponseBody  //http에서 body부분에 데이터를 직접 넣어주겠다.
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello hellApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
//@RequestParam("가져올 데이터의 이름") [데이터타입] [가져온 데이터를 담을 변수]
//http://localhost:8080/hello-mvc?name=spring
