package zx.spring.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import zx.spring.model.QainfoModel;
import zx.spring.service.QainfoService;

@Controller
public class QainfoController {

	@Autowired
	private QainfoService service;

	@RequestMapping("/search")
	public String search(Model model) {
		model.addAttribute("navIndex", 4);
		return "/search";
	}

	@RequestMapping(value = "/searchqa/{carcode}", method = RequestMethod.GET)
	@ResponseBody
	public int getSearch(@PathVariable(required = true) String carcode) {
		int days = -1;
		QainfoModel _qaInfo = service.findByCarCode(carcode);
		if (_qaInfo != null) {
			Calendar cal = Calendar.getInstance();
			Date _addtime = _qaInfo.getAddtime();
			cal.setTime(_addtime);
			cal.add(Calendar.YEAR, _qaInfo.getYear());
			Date _validity = cal.getTime();
			Date _now = new Date();

			long time1 = _validity.getTime();
			long time2 = _now.getTime();

			long diff;
			if (time1 < time2) {
				days = 0;
			} else {
				diff = time1 - time2;
				days = (int) (diff / (24 * 60 * 60 * 1000));
			}

			return days;
		}
		return days;
	}
}
