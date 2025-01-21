package ca.sheridancollege.damor.Lab1NickDamor;

import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookingController {

    @GetMapping("/general-practitioner")
    public String generalPractitioner() {
        return "general";
    }

    @GetMapping("/specialist")
    public String specialist() {
        return "specialist";
    }

    @GetMapping("/pediatrician")
    public String pediatrician() {
        return "pediatrician";
    }

    @GetMapping("/dentist")
    public String dentist() {
        return "dentist";
    }

    @PostMapping("/submit-appointment")
    public String submitAppointment(@RequestParam String name,
                                     @RequestParam String contact,
                                     @RequestParam String date,
                                     @RequestParam String time,
                                     @RequestParam String reason,
                                     @RequestParam(required = false) String insurance,
                                     Model model) {
    	
        LocalDate appointmentDate = LocalDate.parse(date);
        LocalTime appointmentTime = LocalTime.parse(time);

        // Define hospital hours
        LocalTime openingTime = LocalTime.of(7, 0);
        LocalTime closingTime = LocalTime.of(20, 0);
        if (appointmentDate.getDayOfWeek().toString().equalsIgnoreCase("SUNDAY")) {
            openingTime = LocalTime.of(9, 0);
            closingTime = LocalTime.of(18, 0);
        }

        // Validate time
        if (appointmentTime.isBefore(openingTime) || appointmentTime.isAfter(closingTime)) {
            model.addAttribute("errorMessage", "Appointments must be booked during hospital hours: "
                    + openingTime + " to " + closingTime + ".");
            return "pediatrician"; // Return the same form page
        }

        // confirmation page
        model.addAttribute("name", name);
        model.addAttribute("contact", contact);
        model.addAttribute("date", date);
        model.addAttribute("time", time);
        model.addAttribute("reason", reason);
        model.addAttribute("insurance", insurance);
        return "confirmation";
    }



}