<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class FrontController extends AbstractController
{
    /**
     * @Route("/front", name="front")
     */
    public function index(): Response
    {
        return $this->render('front/index.html.twig', [
            'controller_name' => 'FrontController',
        ]);
    }

    /**
     * @Route("/front/shop", name="shop_single")
     */
    public function shop(): Response
    {

        return $this->render('front/shop_single.html.twig');

    }

    /**
     * @Route("/front/cart", name="cart")
     */
    public function cart(): Response
    {

        return $this->render('front/cart.html.twig');

    }

    /**
     * @Route("/front/checkout", name="checkout")
     */
    public function checkout(): Response
    {

        return $this->render('front/checkout.html.twig');

    }
}
