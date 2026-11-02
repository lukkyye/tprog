
package com.soap;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the server package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _BeneficioAlreadyExistsException_QNAME = new QName("http://server/", "BeneficioAlreadyExistsException");
    private final static QName _BeneficioNotFoundException_QNAME = new QName("http://server/", "BeneficioNotFoundException");
    private final static QName _BeneficioSinOrdenesException_QNAME = new QName("http://server/", "BeneficioSinOrdenesException");
    private final static QName _ConsultaNombreAlreadyExistsException_QNAME = new QName("http://server/", "ConsultaNombreAlreadyExistsException");
    private final static QName _ConsultaNotFoundException_QNAME = new QName("http://server/", "ConsultaNotFoundException");
    private final static QName _DiaConsultaAlreadyExistsException_QNAME = new QName("http://server/", "DiaConsultaAlreadyExistsException");
    private final static QName _EmailAlreadyExistsException_QNAME = new QName("http://server/", "EmailAlreadyExistsException");
    private final static QName _EspecialidadAlreadyExistsException_QNAME = new QName("http://server/", "EspecialidadAlreadyExistsException");
    private final static QName _FechaDiaSemanaInvalidaException_QNAME = new QName("http://server/", "FechaDiaSemanaInvalidaException");
    private final static QName _IdAlreadyExistsException_QNAME = new QName("http://server/", "IdAlreadyExistsException");
    private final static QName _MedicoSinEspecialidadException_QNAME = new QName("http://server/", "MedicoSinEspecialidadException");
    private final static QName _NicknameAlreadyExistsException_QNAME = new QName("http://server/", "NicknameAlreadyExistsException");
    private final static QName _NoNumberAvailableException_QNAME = new QName("http://server/", "NoNumberAvailableException");
    private final static QName _NombreClinicaAlreadyExistsException_QNAME = new QName("http://server/", "NombreClinicaAlreadyExistsException");
    private final static QName _NumeroDeRegistroAlreadyExistsException_QNAME = new QName("http://server/", "NumeroDeRegistroAlreadyExistsException");
    private final static QName _ReservaAlreadyExistsException_QNAME = new QName("http://server/", "ReservaAlreadyExistsException");
    private final static QName _SpecialityNotFoundException_QNAME = new QName("http://server/", "SpecialityNotFoundException");
    private final static QName _UserNotFoundException_QNAME = new QName("http://server/", "UserNotFoundException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: server
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BeneficioAlreadyExistsException }
     * 
     * @return
     *     the new instance of {@link BeneficioAlreadyExistsException }
     */
    public BeneficioAlreadyExistsException createBeneficioAlreadyExistsException() {
        return new BeneficioAlreadyExistsException();
    }

    /**
     * Create an instance of {@link BeneficioNotFoundException }
     * 
     * @return
     *     the new instance of {@link BeneficioNotFoundException }
     */
    public BeneficioNotFoundException createBeneficioNotFoundException() {
        return new BeneficioNotFoundException();
    }

    /**
     * Create an instance of {@link BeneficioSinOrdenesException }
     * 
     * @return
     *     the new instance of {@link BeneficioSinOrdenesException }
     */
    public BeneficioSinOrdenesException createBeneficioSinOrdenesException() {
        return new BeneficioSinOrdenesException();
    }

    /**
     * Create an instance of {@link ConsultaNombreAlreadyExistsException }
     * 
     * @return
     *     the new instance of {@link ConsultaNombreAlreadyExistsException }
     */
    public ConsultaNombreAlreadyExistsException createConsultaNombreAlreadyExistsException() {
        return new ConsultaNombreAlreadyExistsException();
    }

    /**
     * Create an instance of {@link ConsultaNotFoundException }
     * 
     * @return
     *     the new instance of {@link ConsultaNotFoundException }
     */
    public ConsultaNotFoundException createConsultaNotFoundException() {
        return new ConsultaNotFoundException();
    }

    /**
     * Create an instance of {@link DiaConsultaAlreadyExistsException }
     * 
     * @return
     *     the new instance of {@link DiaConsultaAlreadyExistsException }
     */
    public DiaConsultaAlreadyExistsException createDiaConsultaAlreadyExistsException() {
        return new DiaConsultaAlreadyExistsException();
    }

    /**
     * Create an instance of {@link EmailAlreadyExistsException }
     * 
     * @return
     *     the new instance of {@link EmailAlreadyExistsException }
     */
    public EmailAlreadyExistsException createEmailAlreadyExistsException() {
        return new EmailAlreadyExistsException();
    }

    /**
     * Create an instance of {@link EspecialidadAlreadyExistsException }
     * 
     * @return
     *     the new instance of {@link EspecialidadAlreadyExistsException }
     */
    public EspecialidadAlreadyExistsException createEspecialidadAlreadyExistsException() {
        return new EspecialidadAlreadyExistsException();
    }

    /**
     * Create an instance of {@link FechaDiaSemanaInvalidaException }
     * 
     * @return
     *     the new instance of {@link FechaDiaSemanaInvalidaException }
     */
    public FechaDiaSemanaInvalidaException createFechaDiaSemanaInvalidaException() {
        return new FechaDiaSemanaInvalidaException();
    }

    /**
     * Create an instance of {@link IdAlreadyExistsException }
     * 
     * @return
     *     the new instance of {@link IdAlreadyExistsException }
     */
    public IdAlreadyExistsException createIdAlreadyExistsException() {
        return new IdAlreadyExistsException();
    }

    /**
     * Create an instance of {@link MedicoSinEspecialidadException }
     * 
     * @return
     *     the new instance of {@link MedicoSinEspecialidadException }
     */
    public MedicoSinEspecialidadException createMedicoSinEspecialidadException() {
        return new MedicoSinEspecialidadException();
    }

    /**
     * Create an instance of {@link NicknameAlreadyExistsException }
     * 
     * @return
     *     the new instance of {@link NicknameAlreadyExistsException }
     */
    public NicknameAlreadyExistsException createNicknameAlreadyExistsException() {
        return new NicknameAlreadyExistsException();
    }

    /**
     * Create an instance of {@link NoNumberAvailableException }
     * 
     * @return
     *     the new instance of {@link NoNumberAvailableException }
     */
    public NoNumberAvailableException createNoNumberAvailableException() {
        return new NoNumberAvailableException();
    }

    /**
     * Create an instance of {@link NombreClinicaAlreadyExistsException }
     * 
     * @return
     *     the new instance of {@link NombreClinicaAlreadyExistsException }
     */
    public NombreClinicaAlreadyExistsException createNombreClinicaAlreadyExistsException() {
        return new NombreClinicaAlreadyExistsException();
    }

    /**
     * Create an instance of {@link NumeroDeRegistroAlreadyExistsException }
     * 
     * @return
     *     the new instance of {@link NumeroDeRegistroAlreadyExistsException }
     */
    public NumeroDeRegistroAlreadyExistsException createNumeroDeRegistroAlreadyExistsException() {
        return new NumeroDeRegistroAlreadyExistsException();
    }

    /**
     * Create an instance of {@link ReservaAlreadyExistsException }
     * 
     * @return
     *     the new instance of {@link ReservaAlreadyExistsException }
     */
    public ReservaAlreadyExistsException createReservaAlreadyExistsException() {
        return new ReservaAlreadyExistsException();
    }

    /**
     * Create an instance of {@link SpecialityNotFoundException }
     * 
     * @return
     *     the new instance of {@link SpecialityNotFoundException }
     */
    public SpecialityNotFoundException createSpecialityNotFoundException() {
        return new SpecialityNotFoundException();
    }

    /**
     * Create an instance of {@link UserNotFoundException }
     * 
     * @return
     *     the new instance of {@link UserNotFoundException }
     */
    public UserNotFoundException createUserNotFoundException() {
        return new UserNotFoundException();
    }

    /**
     * Create an instance of {@link LocalDate }
     * 
     * @return
     *     the new instance of {@link LocalDate }
     */
    public LocalDate createLocalDate() {
        return new LocalDate();
    }

    /**
     * Create an instance of {@link ArrayList }
     * 
     * @return
     *     the new instance of {@link ArrayList }
     */
    public ArrayList createArrayList() {
        return new ArrayList();
    }

    /**
     * Create an instance of {@link DtUsuario }
     * 
     * @return
     *     the new instance of {@link DtUsuario }
     */
    public DtUsuario createDtUsuario() {
        return new DtUsuario();
    }

    /**
     * Create an instance of {@link DtConsulta }
     * 
     * @return
     *     the new instance of {@link DtConsulta }
     */
    public DtConsulta createDtConsulta() {
        return new DtConsulta();
    }

    /**
     * Create an instance of {@link DtBeneficioAnual }
     * 
     * @return
     *     the new instance of {@link DtBeneficioAnual }
     */
    public DtBeneficioAnual createDtBeneficioAnual() {
        return new DtBeneficioAnual();
    }

    /**
     * Create an instance of {@link DtPaciente }
     * 
     * @return
     *     the new instance of {@link DtPaciente }
     */
    public DtPaciente createDtPaciente() {
        return new DtPaciente();
    }

    /**
     * Create an instance of {@link DtResConsultaDetalle }
     * 
     * @return
     *     the new instance of {@link DtResConsultaDetalle }
     */
    public DtResConsultaDetalle createDtResConsultaDetalle() {
        return new DtResConsultaDetalle();
    }

    /**
     * Create an instance of {@link DtResConsulta }
     * 
     * @return
     *     the new instance of {@link DtResConsulta }
     */
    public DtResConsulta createDtResConsulta() {
        return new DtResConsulta();
    }

    /**
     * Create an instance of {@link DtDiaConsulta }
     * 
     * @return
     *     the new instance of {@link DtDiaConsulta }
     */
    public DtDiaConsulta createDtDiaConsulta() {
        return new DtDiaConsulta();
    }

    /**
     * Create an instance of {@link LocalTime }
     * 
     * @return
     *     the new instance of {@link LocalTime }
     */
    public LocalTime createLocalTime() {
        return new LocalTime();
    }

    /**
     * Create an instance of {@link DtMedico }
     * 
     * @return
     *     the new instance of {@link DtMedico }
     */
    public DtMedico createDtMedico() {
        return new DtMedico();
    }

    /**
     * Create an instance of {@link DtClinica }
     * 
     * @return
     *     the new instance of {@link DtClinica }
     */
    public DtClinica createDtClinica() {
        return new DtClinica();
    }

    /**
     * Create an instance of {@link DtConsultaDetalle }
     * 
     * @return
     *     the new instance of {@link DtConsultaDetalle }
     */
    public DtConsultaDetalle createDtConsultaDetalle() {
        return new DtConsultaDetalle();
    }

    /**
     * Create an instance of {@link DtConsultaArray }
     * 
     * @return
     *     the new instance of {@link DtConsultaArray }
     */
    public DtConsultaArray createDtConsultaArray() {
        return new DtConsultaArray();
    }

    /**
     * Create an instance of {@link DtPacienteArray }
     * 
     * @return
     *     the new instance of {@link DtPacienteArray }
     */
    public DtPacienteArray createDtPacienteArray() {
        return new DtPacienteArray();
    }

    /**
     * Create an instance of {@link DtResConsultaArray }
     * 
     * @return
     *     the new instance of {@link DtResConsultaArray }
     */
    public DtResConsultaArray createDtResConsultaArray() {
        return new DtResConsultaArray();
    }

    /**
     * Create an instance of {@link DtDiaConsultaArray }
     * 
     * @return
     *     the new instance of {@link DtDiaConsultaArray }
     */
    public DtDiaConsultaArray createDtDiaConsultaArray() {
        return new DtDiaConsultaArray();
    }

    /**
     * Create an instance of {@link DtUsuarioArray }
     * 
     * @return
     *     the new instance of {@link DtUsuarioArray }
     */
    public DtUsuarioArray createDtUsuarioArray() {
        return new DtUsuarioArray();
    }

    /**
     * Create an instance of {@link DtMedicoArray }
     * 
     * @return
     *     the new instance of {@link DtMedicoArray }
     */
    public DtMedicoArray createDtMedicoArray() {
        return new DtMedicoArray();
    }

    /**
     * Create an instance of {@link DtBeneficioAnualArray }
     * 
     * @return
     *     the new instance of {@link DtBeneficioAnualArray }
     */
    public DtBeneficioAnualArray createDtBeneficioAnualArray() {
        return new DtBeneficioAnualArray();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BeneficioAlreadyExistsException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BeneficioAlreadyExistsException }{@code >}
     */
    @XmlElementDecl(namespace = "http://server/", name = "BeneficioAlreadyExistsException")
    public JAXBElement<BeneficioAlreadyExistsException> createBeneficioAlreadyExistsException(BeneficioAlreadyExistsException value) {
        return new JAXBElement<>(_BeneficioAlreadyExistsException_QNAME, BeneficioAlreadyExistsException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BeneficioNotFoundException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BeneficioNotFoundException }{@code >}
     */
    @XmlElementDecl(namespace = "http://server/", name = "BeneficioNotFoundException")
    public JAXBElement<BeneficioNotFoundException> createBeneficioNotFoundException(BeneficioNotFoundException value) {
        return new JAXBElement<>(_BeneficioNotFoundException_QNAME, BeneficioNotFoundException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BeneficioSinOrdenesException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BeneficioSinOrdenesException }{@code >}
     */
    @XmlElementDecl(namespace = "http://server/", name = "BeneficioSinOrdenesException")
    public JAXBElement<BeneficioSinOrdenesException> createBeneficioSinOrdenesException(BeneficioSinOrdenesException value) {
        return new JAXBElement<>(_BeneficioSinOrdenesException_QNAME, BeneficioSinOrdenesException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaNombreAlreadyExistsException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ConsultaNombreAlreadyExistsException }{@code >}
     */
    @XmlElementDecl(namespace = "http://server/", name = "ConsultaNombreAlreadyExistsException")
    public JAXBElement<ConsultaNombreAlreadyExistsException> createConsultaNombreAlreadyExistsException(ConsultaNombreAlreadyExistsException value) {
        return new JAXBElement<>(_ConsultaNombreAlreadyExistsException_QNAME, ConsultaNombreAlreadyExistsException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaNotFoundException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ConsultaNotFoundException }{@code >}
     */
    @XmlElementDecl(namespace = "http://server/", name = "ConsultaNotFoundException")
    public JAXBElement<ConsultaNotFoundException> createConsultaNotFoundException(ConsultaNotFoundException value) {
        return new JAXBElement<>(_ConsultaNotFoundException_QNAME, ConsultaNotFoundException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DiaConsultaAlreadyExistsException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DiaConsultaAlreadyExistsException }{@code >}
     */
    @XmlElementDecl(namespace = "http://server/", name = "DiaConsultaAlreadyExistsException")
    public JAXBElement<DiaConsultaAlreadyExistsException> createDiaConsultaAlreadyExistsException(DiaConsultaAlreadyExistsException value) {
        return new JAXBElement<>(_DiaConsultaAlreadyExistsException_QNAME, DiaConsultaAlreadyExistsException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmailAlreadyExistsException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EmailAlreadyExistsException }{@code >}
     */
    @XmlElementDecl(namespace = "http://server/", name = "EmailAlreadyExistsException")
    public JAXBElement<EmailAlreadyExistsException> createEmailAlreadyExistsException(EmailAlreadyExistsException value) {
        return new JAXBElement<>(_EmailAlreadyExistsException_QNAME, EmailAlreadyExistsException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EspecialidadAlreadyExistsException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EspecialidadAlreadyExistsException }{@code >}
     */
    @XmlElementDecl(namespace = "http://server/", name = "EspecialidadAlreadyExistsException")
    public JAXBElement<EspecialidadAlreadyExistsException> createEspecialidadAlreadyExistsException(EspecialidadAlreadyExistsException value) {
        return new JAXBElement<>(_EspecialidadAlreadyExistsException_QNAME, EspecialidadAlreadyExistsException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FechaDiaSemanaInvalidaException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FechaDiaSemanaInvalidaException }{@code >}
     */
    @XmlElementDecl(namespace = "http://server/", name = "FechaDiaSemanaInvalidaException")
    public JAXBElement<FechaDiaSemanaInvalidaException> createFechaDiaSemanaInvalidaException(FechaDiaSemanaInvalidaException value) {
        return new JAXBElement<>(_FechaDiaSemanaInvalidaException_QNAME, FechaDiaSemanaInvalidaException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IdAlreadyExistsException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link IdAlreadyExistsException }{@code >}
     */
    @XmlElementDecl(namespace = "http://server/", name = "IdAlreadyExistsException")
    public JAXBElement<IdAlreadyExistsException> createIdAlreadyExistsException(IdAlreadyExistsException value) {
        return new JAXBElement<>(_IdAlreadyExistsException_QNAME, IdAlreadyExistsException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MedicoSinEspecialidadException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MedicoSinEspecialidadException }{@code >}
     */
    @XmlElementDecl(namespace = "http://server/", name = "MedicoSinEspecialidadException")
    public JAXBElement<MedicoSinEspecialidadException> createMedicoSinEspecialidadException(MedicoSinEspecialidadException value) {
        return new JAXBElement<>(_MedicoSinEspecialidadException_QNAME, MedicoSinEspecialidadException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NicknameAlreadyExistsException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link NicknameAlreadyExistsException }{@code >}
     */
    @XmlElementDecl(namespace = "http://server/", name = "NicknameAlreadyExistsException")
    public JAXBElement<NicknameAlreadyExistsException> createNicknameAlreadyExistsException(NicknameAlreadyExistsException value) {
        return new JAXBElement<>(_NicknameAlreadyExistsException_QNAME, NicknameAlreadyExistsException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NoNumberAvailableException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link NoNumberAvailableException }{@code >}
     */
    @XmlElementDecl(namespace = "http://server/", name = "NoNumberAvailableException")
    public JAXBElement<NoNumberAvailableException> createNoNumberAvailableException(NoNumberAvailableException value) {
        return new JAXBElement<>(_NoNumberAvailableException_QNAME, NoNumberAvailableException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NombreClinicaAlreadyExistsException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link NombreClinicaAlreadyExistsException }{@code >}
     */
    @XmlElementDecl(namespace = "http://server/", name = "NombreClinicaAlreadyExistsException")
    public JAXBElement<NombreClinicaAlreadyExistsException> createNombreClinicaAlreadyExistsException(NombreClinicaAlreadyExistsException value) {
        return new JAXBElement<>(_NombreClinicaAlreadyExistsException_QNAME, NombreClinicaAlreadyExistsException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NumeroDeRegistroAlreadyExistsException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link NumeroDeRegistroAlreadyExistsException }{@code >}
     */
    @XmlElementDecl(namespace = "http://server/", name = "NumeroDeRegistroAlreadyExistsException")
    public JAXBElement<NumeroDeRegistroAlreadyExistsException> createNumeroDeRegistroAlreadyExistsException(NumeroDeRegistroAlreadyExistsException value) {
        return new JAXBElement<>(_NumeroDeRegistroAlreadyExistsException_QNAME, NumeroDeRegistroAlreadyExistsException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReservaAlreadyExistsException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ReservaAlreadyExistsException }{@code >}
     */
    @XmlElementDecl(namespace = "http://server/", name = "ReservaAlreadyExistsException")
    public JAXBElement<ReservaAlreadyExistsException> createReservaAlreadyExistsException(ReservaAlreadyExistsException value) {
        return new JAXBElement<>(_ReservaAlreadyExistsException_QNAME, ReservaAlreadyExistsException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SpecialityNotFoundException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SpecialityNotFoundException }{@code >}
     */
    @XmlElementDecl(namespace = "http://server/", name = "SpecialityNotFoundException")
    public JAXBElement<SpecialityNotFoundException> createSpecialityNotFoundException(SpecialityNotFoundException value) {
        return new JAXBElement<>(_SpecialityNotFoundException_QNAME, SpecialityNotFoundException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserNotFoundException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UserNotFoundException }{@code >}
     */
    @XmlElementDecl(namespace = "http://server/", name = "UserNotFoundException")
    public JAXBElement<UserNotFoundException> createUserNotFoundException(UserNotFoundException value) {
        return new JAXBElement<>(_UserNotFoundException_QNAME, UserNotFoundException.class, null, value);
    }

}
