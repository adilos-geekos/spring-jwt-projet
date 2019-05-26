package ma.jwt.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Controlleurs {
	@RequestMapping(name="/adil",method=RequestMethod.GET)
	public String adil() {
		return "adil";
	}
}
