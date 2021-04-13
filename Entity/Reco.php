<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * Reco
 *
 * @ORM\Table(name="reco")
 * @ORM\Entity
 */
class Reco
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string
     *@Assert\NotBlank(message="remplir le champs titre")
     * @ORM\Column(name="titre", type="string", length=30, nullable=false)
     */
    private $titre;

    /**
     * @var string
     *@Assert\NotBlank(message="remplir le champs description")
     * @ORM\Column(name="description", type="string", length=300, nullable=false)
     */
    private $description;

    /**
     * @var string
     *
     *@Assert\NotBlank(message="remplir le champs ecrivain")
     * @ORM\Column(name="ecrivain", type="string", length=30, nullable=false)
     */
    private $ecrivain;

    /**
     * @var string
     *@Assert\NotBlank(message="remplir le champs image")
     * @ORM\Column(name="image", type="string", length=90, nullable=false)
     */
    private $image;

    /**
     * @var string
     *
     *@Assert\NotBlank(message="remplir le champs type")
     * @ORM\Column(name="type", type="string", length=30, nullable=false)
     */
    private $type;

    /**
     * Reco constructor.
     */
    public function __construct()
    {
    }

    /**
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * @param int $id
     */
    public function setId(int $id): void
    {
        $this->id = $id;
    }

    /**
     * @return string
     */
    public function getTitre()
    {
        return $this->titre;
    }

    /**
     * @param string $titre
     */
    public function setTitre(string $titre): void
    {
        $this->titre = $titre;
    }

    /**
     * @return string
     */
    public function getDescription()
    {
        return $this->description;
    }

    /**
     * @param string $description
     */
    public function setDescription(string $description): void
    {
        $this->description = $description;
    }

    /**
     * @return string
     */
    public function getEcrivain()
    {
        return $this->ecrivain;
    }

    /**
     * @param string $ecrivain
     */
    public function setEcrivain(string $ecrivain): void
    {
        $this->ecrivain = $ecrivain;
    }


    public function getImage()
    {
        return $this->image;
    }


    public function setImage( $image)
    {
        $this->image = $image;
    }

    /**
     * @return string
     */
    public function getType()
    {
        return $this->type;
    }

    /**
     * @param string $type
     */
    public function setType(string $type): void
    {
        $this->type = $type;
    }


}
