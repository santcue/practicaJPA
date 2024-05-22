package cue.edu.co.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.Scanner;

public class editar {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese el ID del cliente a actualizar: ");
        Long id = sc.nextLong();
        sc.nextLine();

        System.out.println("Ingrese el nuevo nombre: ");
        String newName = sc.nextLine();

        System.out.println("Ingrese el nuevo apellido: ");
        String newLastName = sc.nextLine();

        System.out.println("Ingrese la nueva forma de pago: ");
        String newFormPay = sc.nextLine();

        EntityManager em = null;
        EntityTransaction transaction = null;

        try {
            em = JpaUtil.getEntityManager();
            transaction = em.getTransaction();
            transaction.begin();

            Cliente client = em.find(Cliente.class, id);
            if (client != null) {
                client.setNombre(newName);
                client.setApellido(newLastName);
                client.setFormaPago(newFormPay);
                em.merge(client);
                System.out.println("Cliente actualizado exitosamente: " + client);
            } else {
                System.out.println("Cliente con ID " + id + " no encontrado.");
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
            JpaUtil.getEntityManager().close();
        }
    }
}
