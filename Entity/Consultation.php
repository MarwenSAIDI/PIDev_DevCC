<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * Consultation
 *
 * @ORM\Table(name="consultation")
 * @ORM\Entity
 */
class Consultation
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
     * @var int
     *
     * @ORM\Column(name="idtherapeute", type="integer", nullable=false)
     */
    private $idtherapeute;

    /**
     * @var string
     * @Assert\NotBlank(message="titre doit etre remplis")
     * @ORM\Column(name="titre", type="string", length=255, nullable=false)
     */
    private $titre;

    /**

     * @ORM\Column(name="description", type="string", length=255, nullable=false)
     * @Assert\NotBlank(message="description doit etre remplis")

     */
    private $description;

    /**
     * @var string
     * @Assert\NotBlank(message="emplacement doit etre remplis")
     * @ORM\Column(name="emplacement", type="string", length=255, nullable=false)
     */
    private $emplacement;

    /**
     * @var float
     * @Assert\Positive

     * @Assert\NotBlank(message="prix doit etre remplis")
     * @ORM\Column(name="prix", type="float", precision=10, scale=0, nullable=false)
     */
    private $prix;

    /**
     * @var string
     * @ORM\Column(name="image", type="string", length=255, nullable=false)
     * @Assert\NotBlank(message="Please upload image")
     * @Assert\File(mimeTypes={"image/jpeg"})
     */
    private $image;
    /**
     * @return int
     */
    public function getId(): int
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
     * @return int
     */
    public function getIdtherapeute()
    {
        return $this->idtherapeute;
    }

    /**
     * @param int $idtherapeute
     */
    public function setIdtherapeute(int $idtherapeute): void
    {
        $this->idtherapeute = $idtherapeute;
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
    public function getEmplacement()
    {
        return $this->emplacement;
    }

    /**
     * @param string $emplacement
     */
    public function setEmplacement(string $emplacement): void
    {
        $this->emplacement = $emplacement;
    }
    /**
     * @return float
     */
    public function getPrix()
    {
        return $this->prix;
    }

    /**
     * @param float $prix
     */
    public function setPrix(float $prix): void
    {
        $this->prix = $prix;
    }


    public function getImage()
    {
        return $this->image;
    }


    public function setImage(string $image)
    {
        $this->image = $image;
        return $this;

    }


}
