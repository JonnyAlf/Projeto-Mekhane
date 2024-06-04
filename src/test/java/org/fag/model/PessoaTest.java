package org.fag.model;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class PessoaTest {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mekhane");
    private static EntityManager entityManager = entityManagerFactory.createEntityManager();

    public static void main(String[] args) {
        try {
            Pessoa pessoa = entityManager.find(Pessoa.class, 1);
            if (pessoa != null) {
                System.out.println("\n Nome pessoa: " + pessoa.getNome() +
                                   "\n CPF ou CNPJ: " + pessoa.getCpfCnpj() +
                                   "\n RG: " + pessoa.getRg() +
                                   "\n Telefone: " + pessoa.getTelefone());
            } else {
                System.out.println("Pessoa não encontrada.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}