// Generated from /Users/oswaldocisneros/Documents/compiler/pj/src/antlr/ProcessJ.g4 by ANTLR 4.12.0
package parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ProcessJParser}.
 */
public interface ProcessJListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void enterCompilationUnit(ProcessJParser.CompilationUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void exitCompilationUnit(ProcessJParser.CompilationUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#packageDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterPackageDeclaration(ProcessJParser.PackageDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#packageDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitPackageDeclaration(ProcessJParser.PackageDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#packageModifier}.
	 * @param ctx the parse tree
	 */
	void enterPackageModifier(ProcessJParser.PackageModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#packageModifier}.
	 * @param ctx the parse tree
	 */
	void exitPackageModifier(ProcessJParser.PackageModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#packageName}.
	 * @param ctx the parse tree
	 */
	void enterPackageName(ProcessJParser.PackageNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#packageName}.
	 * @param ctx the parse tree
	 */
	void exitPackageName(ProcessJParser.PackageNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#importDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterImportDeclaration(ProcessJParser.ImportDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#importDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitImportDeclaration(ProcessJParser.ImportDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#singleTypeImportDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterSingleTypeImportDeclaration(ProcessJParser.SingleTypeImportDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#singleTypeImportDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitSingleTypeImportDeclaration(ProcessJParser.SingleTypeImportDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#singleTypeMultiImportDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterSingleTypeMultiImportDeclaration(ProcessJParser.SingleTypeMultiImportDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#singleTypeMultiImportDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitSingleTypeMultiImportDeclaration(ProcessJParser.SingleTypeMultiImportDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#singleStaticImportDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterSingleStaticImportDeclaration(ProcessJParser.SingleStaticImportDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#singleStaticImportDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitSingleStaticImportDeclaration(ProcessJParser.SingleStaticImportDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#singleStaticMultiImportDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterSingleStaticMultiImportDeclaration(ProcessJParser.SingleStaticMultiImportDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#singleStaticMultiImportDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitSingleStaticMultiImportDeclaration(ProcessJParser.SingleStaticMultiImportDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#typeDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterTypeDeclaration(ProcessJParser.TypeDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#typeDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitTypeDeclaration(ProcessJParser.TypeDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#externDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterExternDeclaration(ProcessJParser.ExternDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#externDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitExternDeclaration(ProcessJParser.ExternDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#procedureTypeDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterProcedureTypeDeclaration(ProcessJParser.ProcedureTypeDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#procedureTypeDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitProcedureTypeDeclaration(ProcessJParser.ProcedureTypeDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#modifier}.
	 * @param ctx the parse tree
	 */
	void enterModifier(ProcessJParser.ModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#modifier}.
	 * @param ctx the parse tree
	 */
	void exitModifier(ProcessJParser.ModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#formarlParameterList}.
	 * @param ctx the parse tree
	 */
	void enterFormarlParameterList(ProcessJParser.FormarlParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#formarlParameterList}.
	 * @param ctx the parse tree
	 */
	void exitFormarlParameterList(ProcessJParser.FormarlParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#formalParameters}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameters(ProcessJParser.FormalParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#formalParameters}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameters(ProcessJParser.FormalParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#formalParameter}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameter(ProcessJParser.FormalParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#formalParameter}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameter(ProcessJParser.FormalParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#variableModifier}.
	 * @param ctx the parse tree
	 */
	void enterVariableModifier(ProcessJParser.VariableModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#variableModifier}.
	 * @param ctx the parse tree
	 */
	void exitVariableModifier(ProcessJParser.VariableModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#lastFormalDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterLastFormalDeclaration(ProcessJParser.LastFormalDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#lastFormalDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitLastFormalDeclaration(ProcessJParser.LastFormalDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#variableDeclaratorIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaratorIdentifier(ProcessJParser.VariableDeclaratorIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#variableDeclaratorIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaratorIdentifier(ProcessJParser.VariableDeclaratorIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(ProcessJParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(ProcessJParser.BodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#recordTypeDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterRecordTypeDeclaration(ProcessJParser.RecordTypeDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#recordTypeDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitRecordTypeDeclaration(ProcessJParser.RecordTypeDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#extends}.
	 * @param ctx the parse tree
	 */
	void enterExtends(ProcessJParser.ExtendsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#extends}.
	 * @param ctx the parse tree
	 */
	void exitExtends(ProcessJParser.ExtendsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#recordBody}.
	 * @param ctx the parse tree
	 */
	void enterRecordBody(ProcessJParser.RecordBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#recordBody}.
	 * @param ctx the parse tree
	 */
	void exitRecordBody(ProcessJParser.RecordBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#recordMemberDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterRecordMemberDeclaration(ProcessJParser.RecordMemberDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#recordMemberDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitRecordMemberDeclaration(ProcessJParser.RecordMemberDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#recordMemberDeclarators}.
	 * @param ctx the parse tree
	 */
	void enterRecordMemberDeclarators(ProcessJParser.RecordMemberDeclaratorsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#recordMemberDeclarators}.
	 * @param ctx the parse tree
	 */
	void exitRecordMemberDeclarators(ProcessJParser.RecordMemberDeclaratorsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#recordMemberDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterRecordMemberDeclarator(ProcessJParser.RecordMemberDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#recordMemberDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitRecordMemberDeclarator(ProcessJParser.RecordMemberDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#protocolTypeDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterProtocolTypeDeclaration(ProcessJParser.ProtocolTypeDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#protocolTypeDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitProtocolTypeDeclaration(ProcessJParser.ProtocolTypeDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#protocolBody}.
	 * @param ctx the parse tree
	 */
	void enterProtocolBody(ProcessJParser.ProtocolBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#protocolBody}.
	 * @param ctx the parse tree
	 */
	void exitProtocolBody(ProcessJParser.ProtocolBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#protocolCase}.
	 * @param ctx the parse tree
	 */
	void enterProtocolCase(ProcessJParser.ProtocolCaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#protocolCase}.
	 * @param ctx the parse tree
	 */
	void exitProtocolCase(ProcessJParser.ProtocolCaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#type_}.
	 * @param ctx the parse tree
	 */
	void enterType_(ProcessJParser.Type_Context ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#type_}.
	 * @param ctx the parse tree
	 */
	void exitType_(ProcessJParser.Type_Context ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#primitiveType}.
	 * @param ctx the parse tree
	 */
	void enterPrimitiveType(ProcessJParser.PrimitiveTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#primitiveType}.
	 * @param ctx the parse tree
	 */
	void exitPrimitiveType(ProcessJParser.PrimitiveTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#referenceType}.
	 * @param ctx the parse tree
	 */
	void enterReferenceType(ProcessJParser.ReferenceTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#referenceType}.
	 * @param ctx the parse tree
	 */
	void exitReferenceType(ProcessJParser.ReferenceTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#numericType}.
	 * @param ctx the parse tree
	 */
	void enterNumericType(ProcessJParser.NumericTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#numericType}.
	 * @param ctx the parse tree
	 */
	void exitNumericType(ProcessJParser.NumericTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#integralType}.
	 * @param ctx the parse tree
	 */
	void enterIntegralType(ProcessJParser.IntegralTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#integralType}.
	 * @param ctx the parse tree
	 */
	void exitIntegralType(ProcessJParser.IntegralTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#floatingPointType}.
	 * @param ctx the parse tree
	 */
	void enterFloatingPointType(ProcessJParser.FloatingPointTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#floatingPointType}.
	 * @param ctx the parse tree
	 */
	void exitFloatingPointType(ProcessJParser.FloatingPointTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#arrayType}.
	 * @param ctx the parse tree
	 */
	void enterArrayType(ProcessJParser.ArrayTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#arrayType}.
	 * @param ctx the parse tree
	 */
	void exitArrayType(ProcessJParser.ArrayTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IdentifierArguments}
	 * labeled alternative in {@link ProcessJParser#classType}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierArguments(ProcessJParser.IdentifierArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IdentifierArguments}
	 * labeled alternative in {@link ProcessJParser#classType}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierArguments(ProcessJParser.IdentifierArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ClassDotIdentifierArguments}
	 * labeled alternative in {@link ProcessJParser#classType}.
	 * @param ctx the parse tree
	 */
	void enterClassDotIdentifierArguments(ProcessJParser.ClassDotIdentifierArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ClassDotIdentifierArguments}
	 * labeled alternative in {@link ProcessJParser#classType}.
	 * @param ctx the parse tree
	 */
	void exitClassDotIdentifierArguments(ProcessJParser.ClassDotIdentifierArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#typeArguments}.
	 * @param ctx the parse tree
	 */
	void enterTypeArguments(ProcessJParser.TypeArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#typeArguments}.
	 * @param ctx the parse tree
	 */
	void exitTypeArguments(ProcessJParser.TypeArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#typeArgument}.
	 * @param ctx the parse tree
	 */
	void enterTypeArgument(ProcessJParser.TypeArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#typeArgument}.
	 * @param ctx the parse tree
	 */
	void exitTypeArgument(ProcessJParser.TypeArgumentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ChannelSharedReadType}
	 * labeled alternative in {@link ProcessJParser#channelType}.
	 * @param ctx the parse tree
	 */
	void enterChannelSharedReadType(ProcessJParser.ChannelSharedReadTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ChannelSharedReadType}
	 * labeled alternative in {@link ProcessJParser#channelType}.
	 * @param ctx the parse tree
	 */
	void exitChannelSharedReadType(ProcessJParser.ChannelSharedReadTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ChannelSharedWriteType}
	 * labeled alternative in {@link ProcessJParser#channelType}.
	 * @param ctx the parse tree
	 */
	void enterChannelSharedWriteType(ProcessJParser.ChannelSharedWriteTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ChannelSharedWriteType}
	 * labeled alternative in {@link ProcessJParser#channelType}.
	 * @param ctx the parse tree
	 */
	void exitChannelSharedWriteType(ProcessJParser.ChannelSharedWriteTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ChannelSharedType}
	 * labeled alternative in {@link ProcessJParser#channelType}.
	 * @param ctx the parse tree
	 */
	void enterChannelSharedType(ProcessJParser.ChannelSharedTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ChannelSharedType}
	 * labeled alternative in {@link ProcessJParser#channelType}.
	 * @param ctx the parse tree
	 */
	void exitChannelSharedType(ProcessJParser.ChannelSharedTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ChannelType_}
	 * labeled alternative in {@link ProcessJParser#channelType}.
	 * @param ctx the parse tree
	 */
	void enterChannelType_(ProcessJParser.ChannelType_Context ctx);
	/**
	 * Exit a parse tree produced by the {@code ChannelType_}
	 * labeled alternative in {@link ProcessJParser#channelType}.
	 * @param ctx the parse tree
	 */
	void exitChannelType_(ProcessJParser.ChannelType_Context ctx);
	/**
	 * Enter a parse tree produced by the {@code ChannelDotRead}
	 * labeled alternative in {@link ProcessJParser#channelType}.
	 * @param ctx the parse tree
	 */
	void enterChannelDotRead(ProcessJParser.ChannelDotReadContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ChannelDotRead}
	 * labeled alternative in {@link ProcessJParser#channelType}.
	 * @param ctx the parse tree
	 */
	void exitChannelDotRead(ProcessJParser.ChannelDotReadContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ChannelDotWrite}
	 * labeled alternative in {@link ProcessJParser#channelType}.
	 * @param ctx the parse tree
	 */
	void enterChannelDotWrite(ProcessJParser.ChannelDotWriteContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ChannelDotWrite}
	 * labeled alternative in {@link ProcessJParser#channelType}.
	 * @param ctx the parse tree
	 */
	void exitChannelDotWrite(ProcessJParser.ChannelDotWriteContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ChannelSharedDotRead}
	 * labeled alternative in {@link ProcessJParser#channelType}.
	 * @param ctx the parse tree
	 */
	void enterChannelSharedDotRead(ProcessJParser.ChannelSharedDotReadContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ChannelSharedDotRead}
	 * labeled alternative in {@link ProcessJParser#channelType}.
	 * @param ctx the parse tree
	 */
	void exitChannelSharedDotRead(ProcessJParser.ChannelSharedDotReadContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ChannelSharedDotWrite}
	 * labeled alternative in {@link ProcessJParser#channelType}.
	 * @param ctx the parse tree
	 */
	void enterChannelSharedDotWrite(ProcessJParser.ChannelSharedDotWriteContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ChannelSharedDotWrite}
	 * labeled alternative in {@link ProcessJParser#channelType}.
	 * @param ctx the parse tree
	 */
	void exitChannelSharedDotWrite(ProcessJParser.ChannelSharedDotWriteContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TypeVariableIdentifier}
	 * labeled alternative in {@link ProcessJParser#typeVariable}.
	 * @param ctx the parse tree
	 */
	void enterTypeVariableIdentifier(ProcessJParser.TypeVariableIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TypeVariableIdentifier}
	 * labeled alternative in {@link ProcessJParser#typeVariable}.
	 * @param ctx the parse tree
	 */
	void exitTypeVariableIdentifier(ProcessJParser.TypeVariableIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TypeVariablePackageDotIdentifier}
	 * labeled alternative in {@link ProcessJParser#typeVariable}.
	 * @param ctx the parse tree
	 */
	void enterTypeVariablePackageDotIdentifier(ProcessJParser.TypeVariablePackageDotIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TypeVariablePackageDotIdentifier}
	 * labeled alternative in {@link ProcessJParser#typeVariable}.
	 * @param ctx the parse tree
	 */
	void exitTypeVariablePackageDotIdentifier(ProcessJParser.TypeVariablePackageDotIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#dims}.
	 * @param ctx the parse tree
	 */
	void enterDims(ProcessJParser.DimsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#dims}.
	 * @param ctx the parse tree
	 */
	void exitDims(ProcessJParser.DimsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#annotation}.
	 * @param ctx the parse tree
	 */
	void enterAnnotation(ProcessJParser.AnnotationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#annotation}.
	 * @param ctx the parse tree
	 */
	void exitAnnotation(ProcessJParser.AnnotationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#normalAnnotation}.
	 * @param ctx the parse tree
	 */
	void enterNormalAnnotation(ProcessJParser.NormalAnnotationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#normalAnnotation}.
	 * @param ctx the parse tree
	 */
	void exitNormalAnnotation(ProcessJParser.NormalAnnotationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#elementValuePairList}.
	 * @param ctx the parse tree
	 */
	void enterElementValuePairList(ProcessJParser.ElementValuePairListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#elementValuePairList}.
	 * @param ctx the parse tree
	 */
	void exitElementValuePairList(ProcessJParser.ElementValuePairListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#elementValuePair}.
	 * @param ctx the parse tree
	 */
	void enterElementValuePair(ProcessJParser.ElementValuePairContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#elementValuePair}.
	 * @param ctx the parse tree
	 */
	void exitElementValuePair(ProcessJParser.ElementValuePairContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#elementValue}.
	 * @param ctx the parse tree
	 */
	void enterElementValue(ProcessJParser.ElementValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#elementValue}.
	 * @param ctx the parse tree
	 */
	void exitElementValue(ProcessJParser.ElementValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#markerAnnotation}.
	 * @param ctx the parse tree
	 */
	void enterMarkerAnnotation(ProcessJParser.MarkerAnnotationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#markerAnnotation}.
	 * @param ctx the parse tree
	 */
	void exitMarkerAnnotation(ProcessJParser.MarkerAnnotationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#singleElementAnnotation}.
	 * @param ctx the parse tree
	 */
	void enterSingleElementAnnotation(ProcessJParser.SingleElementAnnotationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#singleElementAnnotation}.
	 * @param ctx the parse tree
	 */
	void exitSingleElementAnnotation(ProcessJParser.SingleElementAnnotationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#typeName}.
	 * @param ctx the parse tree
	 */
	void enterTypeName(ProcessJParser.TypeNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#typeName}.
	 * @param ctx the parse tree
	 */
	void exitTypeName(ProcessJParser.TypeNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#constantDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterConstantDeclaration(ProcessJParser.ConstantDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#constantDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitConstantDeclaration(ProcessJParser.ConstantDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#constantDeclarators}.
	 * @param ctx the parse tree
	 */
	void enterConstantDeclarators(ProcessJParser.ConstantDeclaratorsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#constantDeclarators}.
	 * @param ctx the parse tree
	 */
	void exitConstantDeclarators(ProcessJParser.ConstantDeclaratorsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#constantDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterConstantDeclarator(ProcessJParser.ConstantDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#constantDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitConstantDeclarator(ProcessJParser.ConstantDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#variableInitializer}.
	 * @param ctx the parse tree
	 */
	void enterVariableInitializer(ProcessJParser.VariableInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#variableInitializer}.
	 * @param ctx the parse tree
	 */
	void exitVariableInitializer(ProcessJParser.VariableInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#arrayInitializer}.
	 * @param ctx the parse tree
	 */
	void enterArrayInitializer(ProcessJParser.ArrayInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#arrayInitializer}.
	 * @param ctx the parse tree
	 */
	void exitArrayInitializer(ProcessJParser.ArrayInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#variableInitializerList}.
	 * @param ctx the parse tree
	 */
	void enterVariableInitializerList(ProcessJParser.VariableInitializerListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#variableInitializerList}.
	 * @param ctx the parse tree
	 */
	void exitVariableInitializerList(ProcessJParser.VariableInitializerListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(ProcessJParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(ProcessJParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void enterBlockStatement(ProcessJParser.BlockStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void exitBlockStatement(ProcessJParser.BlockStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#localVariableDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void enterLocalVariableDeclarationStatement(ProcessJParser.LocalVariableDeclarationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#localVariableDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void exitLocalVariableDeclarationStatement(ProcessJParser.LocalVariableDeclarationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#localVariableDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterLocalVariableDeclaration(ProcessJParser.LocalVariableDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#localVariableDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitLocalVariableDeclaration(ProcessJParser.LocalVariableDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#variableDeclaratorList}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaratorList(ProcessJParser.VariableDeclaratorListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#variableDeclaratorList}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaratorList(ProcessJParser.VariableDeclaratorListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#variableDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclarator(ProcessJParser.VariableDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#variableDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclarator(ProcessJParser.VariableDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BlockAsStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterBlockAsStatement(ProcessJParser.BlockAsStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BlockAsStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitBlockAsStatement(ProcessJParser.BlockAsStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssertStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterAssertStatement(ProcessJParser.AssertStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssertStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitAssertStatement(ProcessJParser.AssertStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IfStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(ProcessJParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IfStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(ProcessJParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ForStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterForStatement(ProcessJParser.ForStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ForStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitForStatement(ProcessJParser.ForStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AltStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterAltStatement(ProcessJParser.AltStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AltStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitAltStatement(ProcessJParser.AltStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AltForStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterAltForStatement(ProcessJParser.AltForStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AltForStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitAltForStatement(ProcessJParser.AltForStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ParStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterParStatement(ProcessJParser.ParStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ParStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitParStatement(ProcessJParser.ParStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ParForStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterParForStatement(ProcessJParser.ParForStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ParForStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitParForStatement(ProcessJParser.ParForStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WhileStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(ProcessJParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WhileStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(ProcessJParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DoStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterDoStatement(ProcessJParser.DoStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DoStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitDoStatement(ProcessJParser.DoStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SwitchStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterSwitchStatement(ProcessJParser.SwitchStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SwitchStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitSwitchStatement(ProcessJParser.SwitchStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ReturnStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(ProcessJParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ReturnStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(ProcessJParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BreakStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterBreakStatement(ProcessJParser.BreakStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BreakStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitBreakStatement(ProcessJParser.BreakStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ContinueStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterContinueStatement(ProcessJParser.ContinueStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ContinueStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitContinueStatement(ProcessJParser.ContinueStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SkipStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterSkipStatement(ProcessJParser.SkipStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SkipStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitSkipStatement(ProcessJParser.SkipStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StopStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStopStatement(ProcessJParser.StopStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StopStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStopStatement(ProcessJParser.StopStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EmptyStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterEmptyStatement(ProcessJParser.EmptyStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EmptyStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitEmptyStatement(ProcessJParser.EmptyStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatementExpression}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatementExpression(ProcessJParser.StatementExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatementExpression}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatementExpression(ProcessJParser.StatementExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LabelStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterLabelStatement(ProcessJParser.LabelStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LabelStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitLabelStatement(ProcessJParser.LabelStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#switchBlockStatementGroup}.
	 * @param ctx the parse tree
	 */
	void enterSwitchBlockStatementGroup(ProcessJParser.SwitchBlockStatementGroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#switchBlockStatementGroup}.
	 * @param ctx the parse tree
	 */
	void exitSwitchBlockStatementGroup(ProcessJParser.SwitchBlockStatementGroupContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#switchLabel}.
	 * @param ctx the parse tree
	 */
	void enterSwitchLabel(ProcessJParser.SwitchLabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#switchLabel}.
	 * @param ctx the parse tree
	 */
	void exitSwitchLabel(ProcessJParser.SwitchLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#forControl}.
	 * @param ctx the parse tree
	 */
	void enterForControl(ProcessJParser.ForControlContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#forControl}.
	 * @param ctx the parse tree
	 */
	void exitForControl(ProcessJParser.ForControlContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#forInit}.
	 * @param ctx the parse tree
	 */
	void enterForInit(ProcessJParser.ForInitContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#forInit}.
	 * @param ctx the parse tree
	 */
	void exitForInit(ProcessJParser.ForInitContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#enhancedForControl}.
	 * @param ctx the parse tree
	 */
	void enterEnhancedForControl(ProcessJParser.EnhancedForControlContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#enhancedForControl}.
	 * @param ctx the parse tree
	 */
	void exitEnhancedForControl(ProcessJParser.EnhancedForControlContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#parExpression}.
	 * @param ctx the parse tree
	 */
	void enterParExpression(ProcessJParser.ParExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#parExpression}.
	 * @param ctx the parse tree
	 */
	void exitParExpression(ProcessJParser.ParExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void enterExpressionList(ProcessJParser.ExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void exitExpressionList(ProcessJParser.ExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#invocation}.
	 * @param ctx the parse tree
	 */
	void enterInvocation(ProcessJParser.InvocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#invocation}.
	 * @param ctx the parse tree
	 */
	void exitInvocation(ProcessJParser.InvocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(ProcessJParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(ProcessJParser.ArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#altBody}.
	 * @param ctx the parse tree
	 */
	void enterAltBody(ProcessJParser.AltBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#altBody}.
	 * @param ctx the parse tree
	 */
	void exitAltBody(ProcessJParser.AltBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#altCase}.
	 * @param ctx the parse tree
	 */
	void enterAltCase(ProcessJParser.AltCaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#altCase}.
	 * @param ctx the parse tree
	 */
	void exitAltCase(ProcessJParser.AltCaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#altGuard}.
	 * @param ctx the parse tree
	 */
	void enterAltGuard(ProcessJParser.AltGuardContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#altGuard}.
	 * @param ctx the parse tree
	 */
	void exitAltGuard(ProcessJParser.AltGuardContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AdditiveExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpression(ProcessJParser.AdditiveExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AdditiveExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpression(ProcessJParser.AdditiveExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RelationalExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterRelationalExpression(ProcessJParser.RelationalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RelationalExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitRelationalExpression(ProcessJParser.RelationalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TernaryExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterTernaryExpression(ProcessJParser.TernaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TernaryExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitTernaryExpression(ProcessJParser.TernaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MemberAccessExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMemberAccessExpression(ProcessJParser.MemberAccessExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MemberAccessExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMemberAccessExpression(ProcessJParser.MemberAccessExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LogicalAndExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalAndExpression(ProcessJParser.LogicalAndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LogicalAndExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalAndExpression(ProcessJParser.LogicalAndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrimaryExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpression(ProcessJParser.PrimaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrimaryExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpression(ProcessJParser.PrimaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ObjectCreationExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterObjectCreationExpression(ProcessJParser.ObjectCreationExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ObjectCreationExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitObjectCreationExpression(ProcessJParser.ObjectCreationExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LogicalOrExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOrExpression(ProcessJParser.LogicalOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LogicalOrExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOrExpression(ProcessJParser.LogicalOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InvocationExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterInvocationExpression(ProcessJParser.InvocationExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InvocationExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitInvocationExpression(ProcessJParser.InvocationExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ShiftExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterShiftExpression(ProcessJParser.ShiftExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ShiftExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitShiftExpression(ProcessJParser.ShiftExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AnnotatedExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAnnotatedExpression(ProcessJParser.AnnotatedExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AnnotatedExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAnnotatedExpression(ProcessJParser.AnnotatedExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArrayAccexxExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterArrayAccexxExpression(ProcessJParser.ArrayAccexxExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArrayAccexxExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitArrayAccexxExpression(ProcessJParser.ArrayAccexxExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExclusiveExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExclusiveExpression(ProcessJParser.ExclusiveExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExclusiveExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExclusiveExpression(ProcessJParser.ExclusiveExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AndExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAndExpression(ProcessJParser.AndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AndExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAndExpression(ProcessJParser.AndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InclusiveExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterInclusiveExpression(ProcessJParser.InclusiveExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InclusiveExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitInclusiveExpression(ProcessJParser.InclusiveExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignmentExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentExpression(ProcessJParser.AssignmentExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignmentExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentExpression(ProcessJParser.AssignmentExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrefixExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPrefixExpression(ProcessJParser.PrefixExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrefixExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPrefixExpression(ProcessJParser.PrefixExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InstanceofExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterInstanceofExpression(ProcessJParser.InstanceofExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InstanceofExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitInstanceofExpression(ProcessJParser.InstanceofExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PostfixExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPostfixExpression(ProcessJParser.PostfixExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PostfixExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPostfixExpression(ProcessJParser.PostfixExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CastExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCastExpression(ProcessJParser.CastExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CastExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCastExpression(ProcessJParser.CastExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EqualityExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterEqualityExpression(ProcessJParser.EqualityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EqualityExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitEqualityExpression(ProcessJParser.EqualityExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MultiplicativeExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpression(ProcessJParser.MultiplicativeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MultiplicativeExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpression(ProcessJParser.MultiplicativeExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#assignOp}.
	 * @param ctx the parse tree
	 */
	void enterAssignOp(ProcessJParser.AssignOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#assignOp}.
	 * @param ctx the parse tree
	 */
	void exitAssignOp(ProcessJParser.AssignOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(ProcessJParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(ProcessJParser.PrimaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#creator}.
	 * @param ctx the parse tree
	 */
	void enterCreator(ProcessJParser.CreatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#creator}.
	 * @param ctx the parse tree
	 */
	void exitCreator(ProcessJParser.CreatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#creatorName}.
	 * @param ctx the parse tree
	 */
	void enterCreatorName(ProcessJParser.CreatorNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#creatorName}.
	 * @param ctx the parse tree
	 */
	void exitCreatorName(ProcessJParser.CreatorNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#arrayExpression}.
	 * @param ctx the parse tree
	 */
	void enterArrayExpression(ProcessJParser.ArrayExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#arrayExpression}.
	 * @param ctx the parse tree
	 */
	void exitArrayExpression(ProcessJParser.ArrayExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#recordExpression}.
	 * @param ctx the parse tree
	 */
	void enterRecordExpression(ProcessJParser.RecordExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#recordExpression}.
	 * @param ctx the parse tree
	 */
	void exitRecordExpression(ProcessJParser.RecordExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#recordExpressionList}.
	 * @param ctx the parse tree
	 */
	void enterRecordExpressionList(ProcessJParser.RecordExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#recordExpressionList}.
	 * @param ctx the parse tree
	 */
	void exitRecordExpressionList(ProcessJParser.RecordExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#protocolExpression}.
	 * @param ctx the parse tree
	 */
	void enterProtocolExpression(ProcessJParser.ProtocolExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#protocolExpression}.
	 * @param ctx the parse tree
	 */
	void exitProtocolExpression(ProcessJParser.ProtocolExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#protocolExpressionList}.
	 * @param ctx the parse tree
	 */
	void enterProtocolExpressionList(ProcessJParser.ProtocolExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#protocolExpressionList}.
	 * @param ctx the parse tree
	 */
	void exitProtocolExpressionList(ProcessJParser.ProtocolExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#tagExpressionList}.
	 * @param ctx the parse tree
	 */
	void enterTagExpressionList(ProcessJParser.TagExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#tagExpressionList}.
	 * @param ctx the parse tree
	 */
	void exitTagExpressionList(ProcessJParser.TagExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#classExpression}.
	 * @param ctx the parse tree
	 */
	void enterClassExpression(ProcessJParser.ClassExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#classExpression}.
	 * @param ctx the parse tree
	 */
	void exitClassExpression(ProcessJParser.ClassExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#typeArgumentsOrDiamond}.
	 * @param ctx the parse tree
	 */
	void enterTypeArgumentsOrDiamond(ProcessJParser.TypeArgumentsOrDiamondContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#typeArgumentsOrDiamond}.
	 * @param ctx the parse tree
	 */
	void exitTypeArgumentsOrDiamond(ProcessJParser.TypeArgumentsOrDiamondContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(ProcessJParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(ProcessJParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link ProcessJParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(ProcessJParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link ProcessJParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(ProcessJParser.IdentifierContext ctx);
}