<?php

namespace App\Form;

use App\Entity\Reservation;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\NumberType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\CheckboxType;

class ReservationType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
        //    ->add('idconsultation')
        //    ->add('idclient')
            ->add('date',DateType::class,
                ['data'   => new \DateTime(),
                    'attr' => ['class'=> 'form-control js-datetimepicker','min' => ( new \DateTime() )->format('Y-m-d')],
                    'required' => false,
                    'widget'=> 'single_text',
                ])
         
            ->add('type',ChoiceType::class,[
                'attr' => ['class' => 'custom-sel'],
                'choices' => [
                    'en ligne     ' => 'en ligne',
                    'a domicile' => 'a domicile',
                ],

                'expanded' => true,
                'multiple' => false,
                'label' => 'Type',
                'required' => true
            ])
                //,['choices'=>array( 'a domicile'=>'a domicile','en ligne'=>'en ligne',),])
            ->add('heure',ChoiceType::class , [
                'attr' => ['class' => 'form-control'],
                'choices' => array( '9:00'=>'9:00','9:30'=>'9:30','10:00'=>'10:00','10:30'=>'10:30','11:00'=>'11:00','11:30'=>'11:30','14:00'=>'14:00','14:30'=>'14:30','15:00'=>'15:00','15:30'=>'15:30')])
           // ->add('etat')
           // ->add('image')
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Reservation::class,
        ]);
    }
}
