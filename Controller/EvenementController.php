<?php

namespace App\Controller;

use App\Entity\Evenement;
use App\Entity\ReservationEvent;

use App\Form\EvenementType;
use App\Form\ReservationEventType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\String\Slugger\SluggerInterface;

/**
 * @Route("/evenement")
 */
class EvenementController extends AbstractController
{
    /**
     * @Route("/", name="evenement_index", methods={"GET"})
     */
    public function index(): Response
    {
        $evenements = $this->getDoctrine()
            ->getRepository(Evenement::class)
            ->findAll();

        return $this->render('evenement/index.html.twig', [
            'evenements' => $evenements,
        ]);
    }
    /**
     * @Route("/evenementfront", name="evenementFront_index", methods={"GET"})
     */
    public function indexFront(): Response
    {
        $evenements = $this->getDoctrine()
            ->getRepository(Evenement::class)
            ->findAll();

        return $this->render('evenement/EvenenementFront.html.twig', [
            'evenements' => $evenements,
        ]);
    }
    /**
     * @Route("/lireplus/{id}", name="lireplusevenement_index", methods={"GET","POST"})
     */
    public function showLirePlusEvent(Request $request,Evenement $evenement): Response
    {

        $ReservationEvent= new ReservationEvent();
        $form = $this->createForm(ReservationEventType::class, $ReservationEvent);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($ReservationEvent);

            $ReservationEvent->setIdOrganisateur($evenement->getIdOrganisateur());
            $ReservationEvent->setIdEvent($evenement->getId());
            $ReservationEvent->setEtat("en cours");
            $ReservationEvent->setTotal($ReservationEvent->getNbPlace()*$evenement->getTarif());
            $entityManager->flush();
            return $this->redirectToRoute('evenementFront_index');
        }




        return $this->render('evenement/lireplusevenement.html.twig', [
            'evenement' => $evenement,
            'formReservation' => $form->createView(),

        ]);


    }
    /**
     * @Route("/new", name="evenement_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $evenement = new Evenement();
        $form = $this->createForm(EvenementType::class, $evenement);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $file=$evenement->getImage();
            $filename= md5(uniqid()).'.'.$file->guessExtension();
            try {
                $file->move(
                    $this->getParameter('imgEvent'),
                    $filename
                );
            } catch (FileException $e) {
                // ... handle exception if something happens during file upload
            }
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($evenement);

            $evenement->setEtat("en cours");
            $evenement->setNbReservation(0);
            $evenement->setImage($filename);

                $entityManager->flush();

            return $this->redirectToRoute('evenement_index');
        }

        return $this->render('evenement/new.html.twig', [
            'evenement' => $evenement,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="evenement_show", methods={"GET"})
     */
    public function show(Evenement $evenement): Response
    {
        return $this->render('evenement/show.html.twig', [
            'evenement' => $evenement,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="evenement_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Evenement $evenement): Response
    {
        $form = $this->createForm(EvenementType::class, $evenement);
        $form->handleRequest($request);



        if ($form->isSubmitted() && $form->isValid()) {
            $file=$evenement->getImage();
            $filename= md5(uniqid()).'.'.$file->guessExtension();
            try {
                $file->move(
                    $this->getParameter('imgEvent'),
                    $filename
                );
            } catch (FileException $e) {
                // ... handle exception if something happens during file upload
            }
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($evenement);
            $evenement->setImage($filename);

            $entityManager->flush();

            return $this->redirectToRoute('evenement_index');
        }


        return $this->render('evenement/edit.html.twig', [
            'evenement' => $evenement,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}/delete", name="evenement_delete", methods={"GET","POST"})
     */
    public function delete(Request $request): Response
    {

        $id = $request->get("id");
        $evenement = $this->getDoctrine()->getRepository(Evenement::class)->find($id);
        $em = $this->getDoctrine()->getManager();
        $em->remove($evenement);
        $em->flush();



        return $this->redirectToRoute('evenement_index');
    }
}
