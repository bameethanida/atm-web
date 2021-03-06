package th.ac.ku.atm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.atm.model.BankAccount;
import th.ac.ku.atm.service.BankAccountService;

@Controller
@RequestMapping("/bankaccount")
public class BankAccountController {

    private BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping
    public String getCustomerPage(Model model) {
        model.addAttribute("allBankAccounts", bankAccountService.getBankAccounts());
        return "bankaccount";
    }
    @PostMapping
    public String openAccount(@ModelAttribute BankAccount bankAccount, Model model) {
        bankAccountService.openAccount(bankAccount);
        model.addAttribute("allBankAccounts",bankAccountService.getBankAccounts());
        return "redirect:bankaccount";
    }

    @GetMapping("/transaction/{id}")
    public String getEditBankAccountPage(@PathVariable int id, Model model) {
        BankAccount account = bankAccountService.getBankAccount(id);
        model.addAttribute("allBankAccounts", account);
        return "bankaccount-edit";
    }

    @PostMapping("/transaction/{id}")
    public String editAccount(@PathVariable int id,
                              @ModelAttribute BankAccount bankAccount,
                              Model model) {

        bankAccountService.editBankAccount(bankAccount);
        model.addAttribute("allBankAccounts",bankAccountService.getBankAccounts());
        return "redirect:/bankaccount";
    }

    @PostMapping("delete/{id}")
    public String deleteAccount(@PathVariable int id,
                                @ModelAttribute BankAccount bankAccount,
                                Model model) {
        bankAccountService.deleteBankAccount(bankAccount);
        model.addAttribute("allBankAccounts",bankAccountService.getBankAccounts());
        return "redirect:/bankaccount";
    }



}
