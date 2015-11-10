# Grammar

    Identifier:
        - IDENTIFIER

    QualifiedIdentifier:
        - Identifier { . Identifier }

    QualifiedIdentifierList: 
        - QualifiedIdentifier { , QualifiedIdentifier }

_______________________

    CompilationUnit: 
        [[Annotations] package QualifiedIdentifier ;]
                                {ImportDeclaration} {TypeDeclaration}

    ImportDeclaration: 
        import [static] Identifier { . Identifier } [. *] ;

    TypeDeclaration: 
        ClassOrInterfaceDeclaration
        ;

    ClassOrInterfaceDeclaration: 
        {Modifier} ClassDeclaration

    ClassDeclaration: 
        NormalClassDeclaration
        EnumDeclaration

    NormalClassDeclaration: 
        class Identifier [TypeParameters]
                                [extends Type] [implements TypeList] ClassBody

    EnumDeclaration:
        enum Identifier [implements TypeList] EnumBody

______________________________

    Type:
        BasicType {[]}
        ReferenceType  {[]}

    BasicType: 
        byte, short, char, int, long, float, double, boolean

    ReferenceType:
        Identifier [TypeArguments] { . Identifier [TypeArguments] }

    TypeArguments: 
        < TypeArgument { , TypeArgument } >

    TypeArgument:  
        ReferenceType
        ? [ (extends | super) ReferenceType ]
        
_______________________________

    NonWildcardTypeArguments:
        < TypeList >

    TypeList:  
        ReferenceType { , ReferenceType }

    TypeArgumentsOrDiamond:
        < > 
        TypeArguments

    NonWildcardTypeArgumentsOrDiamond:
        < >
        NonWildcardTypeArguments

    TypeParameters:
        < TypeParameter { , TypeParameter } >

    TypeParameter:
        Identifier

_____________________________

    Modifier: 
        public, protected, private, static, abstract, final, native, synchronized, transient, volatile, strictfp

    ElementValue:
        Expression1 
        ElementValueArrayInitializer

    ElementValueArrayInitializer:
        { [ElementValues] [,] }

    ElementValues:
        ElementValue { , ElementValue }
        
_____________________________

    ClassBody: 
        { { ClassBodyDeclaration } }

    ClassBodyDeclaration:
        ; 
        {Modifier} MemberDecl
        [static] Block

    MemberDecl:
        MethodOrFieldDecl
        void Identifier VoidMethodDeclaratorRest
        Identifier ConstructorDeclaratorRest
        GenericMethodOrConstructorDecl
        ClassDeclaration
        
    MethodOrFieldDecl:
        Type Identifier MethodOrFieldRest

    MethodOrFieldRest:  
        FieldDeclaratorsRest ;
        MethodDeclaratorRest

    FieldDeclaratorsRest:  
        VariableDeclaratorRest { , VariableDeclarator }

    MethodDeclaratorRest:
        FormalParameters {[]} [throws QualifiedIdentifierList] (Block | ;)

    VoidMethodDeclaratorRest:
        FormalParameters [throws QualifiedIdentifierList] (Block | ;)

    ConstructorDeclaratorRest:
        FormalParameters [throws QualifiedIdentifierList] Block

    GenericMethodOrConstructorDecl:
        TypeParameters GenericMethodOrConstructorRest

    GenericMethodOrConstructorRest:
        (Type | void) Identifier MethodDeclaratorRest
        Identifier ConstructorDeclaratorRest

_________________________________

    FormalParameters: 
        ( [FormalParameterDecls] )
    
    FormalParameterDecls: 
        {VariableModifier}  Type FormalParameterDeclsRest
    
    VariableModifier:
        final
        Annotation
    
    FormalParameterDeclsRest: 
        VariableDeclaratorId [, FormalParameterDecls]
        ... VariableDeclaratorId
    
    VariableDeclaratorId:
        Identifier {[]}
    
    VariableDeclarators:
        VariableDeclarator { , VariableDeclarator }
    
    VariableDeclarator:
        Identifier VariableDeclaratorRest
    
    VariableDeclaratorRest:
        {[]} [ = VariableInitializer ]
    
    VariableInitializer:
        ArrayInitializer
        Expression
    
    ArrayInitializer:
        { [ VariableInitializer { , VariableInitializer } [,] ] }
        
____________________________________

    Block: 
        { BlockStatements }
    
    BlockStatements: 
        { BlockStatement }
    
    BlockStatement:
        LocalVariableDeclarationStatement
        ClassOrInterfaceDeclaration
        [Identifier :] Statement
    
    LocalVariableDeclarationStatement:
        { VariableModifier }  Type VariableDeclarators ;
    
    Statement:
        Block
        ;
        Identifier : Statement
        StatementExpression ;
        if ParExpression Statement [else Statement] 
        assert Expression [: Expression] ;
        switch ParExpression { SwitchBlockStatementGroups } 
        while ParExpression Statement
        do Statement while ParExpression ;
        for ( ForControl ) Statement
        break [Identifier] ;
        continue [Identifier] ;
        return [Expression] ;
        throw Expression ;
        synchronized ParExpression Block
        try Block (Catches | [Catches] Finally)
        try ResourceSpecification Block [Catches] [Finally]
    
    StatementExpression: 
        Expression
        
______________________________

    Catches:
        CatchClause { CatchClause }
    
    CatchClause:  
        catch ( {VariableModifier} CatchType Identifier ) Block
    
    CatchType:
        QualifiedIdentifier { | QualifiedIdentifier }
    
    Finally:
        finally Block
    
    ResourceSpecification:
        ( Resources [;] )
    
    Resources:
        Resource { ; Resource }
    
    Resource:
        {VariableModifier} ReferenceType VariableDeclaratorId = Expression 

_________________________________

    SwitchBlockStatementGroups: 
        { SwitchBlockStatementGroup }
    
    SwitchBlockStatementGroup: 
        SwitchLabels BlockStatements
    
    SwitchLabels:
        SwitchLabel { SwitchLabel }
    
    SwitchLabel: 
        case Expression :
        case EnumConstantName :
        default :
    
    EnumConstantName:
        Identifier
    
    
    
    ForControl:
        ForVarControl
        ForInit ; [Expression] ; [ForUpdate]
    
    ForVarControl:
        {VariableModifier} Type VariableDeclaratorId  ForVarControlRest
    
    ForVarControlRest:
        ForVariableDeclaratorsRest ; [Expression] ; [ForUpdate]
        : Expression
    
    ForVariableDeclaratorsRest:
        [= VariableInitializer] { , VariableDeclarator }
    
    ForInit: 
    ForUpdate:
        StatementExpression { , StatementExpression }
        
__________________________

    Expression: 
        Expression1 [AssignmentOperator Expression1]
    
    AssignmentOperator: 
        = 
        +=
        -= 
        *=
        /=
        &=
        |=
        ^=
        %=
        <<=
        >>=
        >>>=
    
    Expression1: 
        Expression2 [Expression1Rest]
    
    Expression1Rest: 
        ? Expression : Expression1
    
    Expression2:
        Expression3 [Expression2Rest]
    
    Expression2Rest:
        { InfixOp Expression3 }
        instanceof Type
        
_______________________________

    InfixOp: 
        || 
        &&
        |
        ^
        &
        ==
        !=
        <
        >
        <=
        >=
        <<
        >>
        >>>
        +
        -
        *
        /
        %
    
    Expression3: 
        PrefixOp Expression3
        ( (Expression | Type) ) Expression3
        Primary { Selector } { PostfixOp }
    
    PrefixOp: 
        ++
        --
        !
        ~
        +
        -
    
    PostfixOp: 
        ++
        --
        
_______________________________

    Primary: 
        Literal
        ParExpression
        this [Arguments]
        super SuperSuffix
        new Creator
        NonWildcardTypeArguments (ExplicitGenericInvocationSuffix | this Arguments)
        Identifier { . Identifier } [IdentifierSuffix]
        BasicType {[]} . class
        void . class
    
    Literal:
        IntegerLiteral
        FloatingPointLiteral
        CharacterLiteral 
        StringLiteral 
        BooleanLiteral
        NullLiteral
    
    ParExpression: 
        ( Expression )
    
    Arguments:
        ( [ Expression { , Expression } ] )
    
    SuperSuffix: 
        Arguments 
        . Identifier [Arguments]
    
    ExplicitGenericInvocationSuffix: 
        super SuperSuffix
        Identifier Arguments

______________________________

    Creator:  
        NonWildcardTypeArguments CreatedName ClassCreatorRest
        CreatedName (ClassCreatorRest | ArrayCreatorRest)
    
    CreatedName:   
        Identifier [TypeArgumentsOrDiamond] { . Identifier [TypeArgumentsOrDiamond] }
    
    ClassCreatorRest: 
        Arguments [ClassBody]
    
    ArrayCreatorRest:
        [ (] {[]} ArrayInitializer  |  Expression ] {[ Expression ]} {[]})
    
    
    
    IdentifierSuffix:
        [ ({[]} . class | Expression) ]
        Arguments 
        . (class | ExplicitGenericInvocation | this | super Arguments |
                                    new [NonWildcardTypeArguments] InnerCreator)
    
    ExplicitGenericInvocation:
        NonWildcardTypeArguments ExplicitGenericInvocationSuffix
    
    InnerCreator:  
        Identifier [NonWildcardTypeArgumentsOrDiamond] ClassCreatorRest
    
    
    
    Selector:
        . Identifier [Arguments]
        . ExplicitGenericInvocation
        . this
        . super SuperSuffix
        . new [NonWildcardTypeArguments] InnerCreator
        [ Expression ]
        
____________________________

    EnumBody:
        { [EnumConstants] [,] [EnumBodyDeclarations] }
    
    EnumConstants:
        EnumConstant
        EnumConstants , EnumConstant
    
    EnumConstant:
        [Annotations] Identifier [Arguments] [ClassBody]
    
    EnumBodyDeclarations:
        ; {ClassBodyDeclaration}