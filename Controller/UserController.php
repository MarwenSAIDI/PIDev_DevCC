<?php

namespace App\Controller;

use App\Entity\User;
use App\Form\LoginType;
use App\Form\UserType;
use App\Repository\TherapeuteRepository;
use App\Repository\UserRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Session\Session;

/**
 * @Route("/user")
 */
class UserController extends AbstractController
{
    /**
     * @Route("/", name="user_index", methods={"GET"})
     */
    public function index(): Response
    {
        $users = $this->getDoctrine()
            ->getRepository(User::class)
            ->findBy(array('type' => 'client'));

        return $this->render('user/index.html.twig', [
            'users' => $users,
        ]);
    }

    /**
     * @Route("/new", name="user_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $user = new User();
        $form = $this->createForm(UserType::class, $user);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $user->setType("client");
            $user->setEtat("attente");
            $entityManager->persist($user);
            $entityManager->flush();

            return $this->redirectToRoute('user_index');
        }

        return $this->render('user/inscription.html.twig', [
            'user' => $user,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}/ban", name="user_ban", methods={"GET","POST"})
     */
    public function ban(Request $request, User $user): Response
    {
        $user->setEtat("banned");

        $this->getDoctrine()->getManager()->flush();

        return $this->redirectToRoute('user_index');


    }

    /**
     * @Route("/{id}/confirm", name="user_confirm", methods={"GET","POST"})
     */
    public function confirm(Request $request, User $user): Response
    {
        $user->setEtat("inscrit");

            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('user_index');


    }

    /**
     * @Route("/{id}", name="user_delete", methods={"POST"})
     */
    public function delete(Request $request, User $user): Response
    {
        if ($this->isCsrfTokenValid('delete'.$user->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($user);
            $entityManager->flush();
        }

        return $this->redirectToRoute('user_index');
    }


    /**
     * @Route("/login/login", name="user_login", methods={"GET","POST"})
     */
    public function login(Request $request,UserRepository $userrep,TherapeuteRepository $repository){
        $user=new User();
        $session = new Session();

        $form = $this->createForm(LoginType::class, $user);
        $form->handleRequest($request);

        if ($form->isSubmitted() ) {
            //$session->start();

            $email=$user->getEmail();
            $mdp=$user->getPassword();
            $etat="inscrit";
            $type="admin";

            $user1=$userrep->finduser($email,$mdp,$etat);
            $user2=$repository->findThere($email,$mdp);
            $user3=$userrep->findadmin($email,$mdp,$type);
            if($user3 !=  null){
                $session->set($email,$mdp);
                return $this->redirectToRoute("therapeute_index");

            }
            if($user2 !=  null){
                return $this->redirectToRoute("reco_index");

            }
            if($user1 != null){
                return $this->redirectToRoute("front");
            }
else {
    $this->addFlash('notice', 'Verifier cos parametres ou vous etes exclu!');

}



        }

        return $this->render('user/Login.html.twig', [

            'form' => $form->createView(),
        ]);

    }

    /**
     * @Route("/log/log", name="user_log", methods={"GET","POST"})
     */
    public function log():Response{

        return $this->render("user/Login.html.twig");
    }
}
