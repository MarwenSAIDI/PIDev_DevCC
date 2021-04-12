<?php

namespace App\Controller;

use App\Entity\Consultation;
use App\Entity\Reservation;
use App\Form\ConsultationType;
use App\Form\ReservationType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Common\Persistence\ManagerRegistry;
use Symfony\Component\HttpFoundation\File\File;

/**
 * @Route("/consultation")
 */
class ConsultationController extends AbstractController
{
    /**
     * @Route("/", name="consultation_index", methods={"GET"})
     */
    public function index(): Response
    {
        $consultations = $this->getDoctrine()
            ->getRepository(Consultation::class)
            ->findAll();

        return $this->render('consultation/index.html.twig', [
            'consultations' => $consultations,
        ]);
    }

    /**
     * @Route("/new", name="consultation_new", methods={"GET","POST"})
     */

    public function new(Request $request): Response
    {

        $consultation = new Consultation();
        $form = $this->createForm(ConsultationType::class, $consultation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $file = $form->get('image')->getData();
            $fileName = md5(uniqid()).'.'.$file->guessExtension();
            try {
                $file->move(
                    $this->getParameter('images_directory'),
                    $fileName
                );
            } catch (FileException $e) {
                // ... handle exception if something happens during file upload
            }
            $entityManager = $this->getDoctrine()->getManager();
            $consultation->setImage($fileName);

            $entityManager->persist($consultation);
            $entityManager->flush();

            return $this->redirectToRoute('consultation_index');
        }

        return $this->render('consultation/new.html.twig', [
            'consultation' => $consultation,
            'form' => $form->createView(),
        ]);
    }
    /**
     * @Route("/affichagefront", name="consultation_afficher", methods={"GET"})
     */
    public function afficherconsultation()
    {
        $consultations = $this->getDoctrine()
            ->getRepository(Consultation::class)
            ->findAll();

        return $this->render('consultation/affichagefront.html.twig', [
            'consultations' => $consultations,
        ]);
    }

    /**
     * @Route("/{id}", name="consultation_show", methods={"GET"})
     */
    public function show(Consultation $consultation): Response
    {
        return $this->render('consultation/show.html.twig', [
            'consultation' => $consultation,
        ]);
    }
    /**
     * @Route("/resform/{id}", name="res_show", methods={"GET"})
     */
    public function afficherres(Request $request,Consultation $consultation): Response
    {
        $reservation = new Reservation();

        $form = $this->createForm(ReservationType::class, $reservation);
        $form->handleRequest($request);

        return $this->render('consultation/resform.html.twig', [
            'consultation' => $consultation,
            'formreservation' => $form->CreateView(),
        ]);
    }

    /**
     * @Route("/{id}/edit", name="consultation_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Consultation $consultation): Response
    {
        $consultation->setImage(
            new File($this->getParameter('images_directory').'/'.$consultation->getImage())
        );
        $form = $this->createForm(ConsultationType::class, $consultation);

        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {

            $file = $form->get('image')->getData();
            $fileName = md5(uniqid()).'.'.$file->guessExtension();
            try {
                $file->move(
                    $this->getParameter('images_directory'),
                    $fileName
                );
            } catch (FileException $e) {
                // ... handle exception if something happens during file upload
            }
            $consultation->setImage($fileName);

            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('consultation_index');

        }

        return $this->render('consultation/edit.html.twig', [
            'consultation' => $consultation,
            'form' => $form->createView(),
        ]);

    }

    /**
     * @Route("/{id}/delete/", name="consultation_delete", methods={"GET","POST"})
     */
    public function delete(Request $request): Response
    {
        $id = $request->get("id");
        $consultation = $this->getDoctrine()->getRepository(Consultation::class)->find($id);
        $em = $this->getDoctrine()->getManager();
        $em->remove($consultation);
        $em->flush();

        return $this->redirectToRoute('consultation_index');
    }
}
