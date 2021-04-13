<?php

namespace App\Form;

use App\Entity\Therapeute;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\NumberType;
use Symfony\Component\Form\Extension\Core\Type\PasswordType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class TherapeuteType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder

            ->add('cin',NumberType::class)
            ->add('email')
            ->add('nom')
            ->add('prenom')
            ->add('password',PasswordType::class)
            ->add('numtel',NumberType::class)
            ->add('adresse')
            ->add('image',FileType::class, array('data_class' => null))
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Therapeute::class,
        ]);
    }
}
