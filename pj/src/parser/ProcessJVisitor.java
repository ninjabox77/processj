// Generated from /Users/oswaldocisneros/Documents/compiler/pj/src/antlr/ProcessJ.g4 by ANTLR 4.12.0
package parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ProcessJParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ProcessJVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#compilationUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompilationUnit(ProcessJParser.CompilationUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#packageDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPackageDeclaration(ProcessJParser.PackageDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#packageModifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPackageModifier(ProcessJParser.PackageModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#packageName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPackageName(ProcessJParser.PackageNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#importDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportDeclaration(ProcessJParser.ImportDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#singleTypeImportDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleTypeImportDeclaration(ProcessJParser.SingleTypeImportDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#singleTypeMultiImportDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleTypeMultiImportDeclaration(ProcessJParser.SingleTypeMultiImportDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#singleStaticImportDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleStaticImportDeclaration(ProcessJParser.SingleStaticImportDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#singleStaticMultiImportDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleStaticMultiImportDeclaration(ProcessJParser.SingleStaticMultiImportDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#typeDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDeclaration(ProcessJParser.TypeDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#externDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExternDeclaration(ProcessJParser.ExternDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#procedureTypeDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedureTypeDeclaration(ProcessJParser.ProcedureTypeDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#modifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModifier(ProcessJParser.ModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#formarlParameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormarlParameterList(ProcessJParser.FormarlParameterListContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#formalParameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalParameters(ProcessJParser.FormalParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#formalParameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalParameter(ProcessJParser.FormalParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#variableModifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableModifier(ProcessJParser.VariableModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#lastFormalDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLastFormalDeclaration(ProcessJParser.LastFormalDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#variableDeclaratorIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclaratorIdentifier(ProcessJParser.VariableDeclaratorIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBody(ProcessJParser.BodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#recordTypeDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecordTypeDeclaration(ProcessJParser.RecordTypeDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#extends}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExtends(ProcessJParser.ExtendsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#recordBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecordBody(ProcessJParser.RecordBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#recordMemberDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecordMemberDeclaration(ProcessJParser.RecordMemberDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#recordMemberDeclarators}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecordMemberDeclarators(ProcessJParser.RecordMemberDeclaratorsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#recordMemberDeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecordMemberDeclarator(ProcessJParser.RecordMemberDeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#protocolTypeDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProtocolTypeDeclaration(ProcessJParser.ProtocolTypeDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#protocolBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProtocolBody(ProcessJParser.ProtocolBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#protocolCase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProtocolCase(ProcessJParser.ProtocolCaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#type_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_(ProcessJParser.Type_Context ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#primitiveType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitiveType(ProcessJParser.PrimitiveTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#referenceType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReferenceType(ProcessJParser.ReferenceTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#numericType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericType(ProcessJParser.NumericTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#integralType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegralType(ProcessJParser.IntegralTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#floatingPointType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatingPointType(ProcessJParser.FloatingPointTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#arrayType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayType(ProcessJParser.ArrayTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IdentifierArguments}
	 * labeled alternative in {@link ProcessJParser#classType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierArguments(ProcessJParser.IdentifierArgumentsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ClassDotIdentifierArguments}
	 * labeled alternative in {@link ProcessJParser#classType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDotIdentifierArguments(ProcessJParser.ClassDotIdentifierArgumentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#typeArguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeArguments(ProcessJParser.TypeArgumentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#typeArgument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeArgument(ProcessJParser.TypeArgumentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ChannelSharedReadType}
	 * labeled alternative in {@link ProcessJParser#channelType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChannelSharedReadType(ProcessJParser.ChannelSharedReadTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ChannelSharedWriteType}
	 * labeled alternative in {@link ProcessJParser#channelType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChannelSharedWriteType(ProcessJParser.ChannelSharedWriteTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ChannelSharedType}
	 * labeled alternative in {@link ProcessJParser#channelType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChannelSharedType(ProcessJParser.ChannelSharedTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ChannelType_}
	 * labeled alternative in {@link ProcessJParser#channelType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChannelType_(ProcessJParser.ChannelType_Context ctx);
	/**
	 * Visit a parse tree produced by the {@code ChannelDotRead}
	 * labeled alternative in {@link ProcessJParser#channelType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChannelDotRead(ProcessJParser.ChannelDotReadContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ChannelDotWrite}
	 * labeled alternative in {@link ProcessJParser#channelType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChannelDotWrite(ProcessJParser.ChannelDotWriteContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ChannelSharedDotRead}
	 * labeled alternative in {@link ProcessJParser#channelType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChannelSharedDotRead(ProcessJParser.ChannelSharedDotReadContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ChannelSharedDotWrite}
	 * labeled alternative in {@link ProcessJParser#channelType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChannelSharedDotWrite(ProcessJParser.ChannelSharedDotWriteContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#typeVariable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeVariable(ProcessJParser.TypeVariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#dims}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDims(ProcessJParser.DimsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#annotation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotation(ProcessJParser.AnnotationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#normalAnnotation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNormalAnnotation(ProcessJParser.NormalAnnotationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#elementValuePairList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElementValuePairList(ProcessJParser.ElementValuePairListContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#elementValuePair}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElementValuePair(ProcessJParser.ElementValuePairContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#elementValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElementValue(ProcessJParser.ElementValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#markerAnnotation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMarkerAnnotation(ProcessJParser.MarkerAnnotationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#singleElementAnnotation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleElementAnnotation(ProcessJParser.SingleElementAnnotationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#typeName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeName(ProcessJParser.TypeNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#constantDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantDeclaration(ProcessJParser.ConstantDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#constantDeclarators}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantDeclarators(ProcessJParser.ConstantDeclaratorsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#constantDeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantDeclarator(ProcessJParser.ConstantDeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#variableInitializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableInitializer(ProcessJParser.VariableInitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#arrayInitializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayInitializer(ProcessJParser.ArrayInitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#variableInitializerList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableInitializerList(ProcessJParser.VariableInitializerListContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(ProcessJParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#blockStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStatement(ProcessJParser.BlockStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#localVariableDeclarationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocalVariableDeclarationStatement(ProcessJParser.LocalVariableDeclarationStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#localVariableDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocalVariableDeclaration(ProcessJParser.LocalVariableDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#variableDeclaratorList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclaratorList(ProcessJParser.VariableDeclaratorListContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#variableDeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclarator(ProcessJParser.VariableDeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BlockAsStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockAsStatement(ProcessJParser.BlockAsStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AssertStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssertStatement(ProcessJParser.AssertStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IfStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(ProcessJParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ForStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStatement(ProcessJParser.ForStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AltStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAltStatement(ProcessJParser.AltStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AltForStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAltForStatement(ProcessJParser.AltForStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParStatement(ProcessJParser.ParStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParForStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParForStatement(ProcessJParser.ParForStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code WhileStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatement(ProcessJParser.WhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DoStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoStatement(ProcessJParser.DoStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SwitchStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchStatement(ProcessJParser.SwitchStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ReturnStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStatement(ProcessJParser.ReturnStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BreakStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStatement(ProcessJParser.BreakStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ContinueStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueStatement(ProcessJParser.ContinueStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SeqStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSeqStatement(ProcessJParser.SeqStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SkipStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSkipStatement(ProcessJParser.SkipStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StopStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStopStatement(ProcessJParser.StopStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EmptyStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyStatement(ProcessJParser.EmptyStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatementExpression}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementExpression(ProcessJParser.StatementExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LabelStatement}
	 * labeled alternative in {@link ProcessJParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabelStatement(ProcessJParser.LabelStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#switchBlockStatementGroup}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchBlockStatementGroup(ProcessJParser.SwitchBlockStatementGroupContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#switchLabel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchLabel(ProcessJParser.SwitchLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#forControl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForControl(ProcessJParser.ForControlContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#forInit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForInit(ProcessJParser.ForInitContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#enhancedForControl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnhancedForControl(ProcessJParser.EnhancedForControlContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#parExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParExpression(ProcessJParser.ParExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#expressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionList(ProcessJParser.ExpressionListContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#invocation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInvocation(ProcessJParser.InvocationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#arguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArguments(ProcessJParser.ArgumentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#altBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAltBody(ProcessJParser.AltBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#altCase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAltCase(ProcessJParser.AltCaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#altGuard}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAltGuard(ProcessJParser.AltGuardContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AdditiveExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveExpression(ProcessJParser.AdditiveExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RelationalExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalExpression(ProcessJParser.RelationalExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TernaryExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTernaryExpression(ProcessJParser.TernaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MemberAccessExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberAccessExpression(ProcessJParser.MemberAccessExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LogicalAndExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalAndExpression(ProcessJParser.LogicalAndExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrimaryExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExpression(ProcessJParser.PrimaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ObjectCreationExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectCreationExpression(ProcessJParser.ObjectCreationExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LogicalOrExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalOrExpression(ProcessJParser.LogicalOrExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InvocationExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInvocationExpression(ProcessJParser.InvocationExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ShiftExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShiftExpression(ProcessJParser.ShiftExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AnnotatedExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotatedExpression(ProcessJParser.AnnotatedExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArrayAccexxExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayAccexxExpression(ProcessJParser.ArrayAccexxExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExclusiveExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExclusiveExpression(ProcessJParser.ExclusiveExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AndExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpression(ProcessJParser.AndExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InclusiveExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInclusiveExpression(ProcessJParser.InclusiveExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AssignmentExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentExpression(ProcessJParser.AssignmentExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrefixExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrefixExpression(ProcessJParser.PrefixExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InstanceofExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstanceofExpression(ProcessJParser.InstanceofExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PostfixExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostfixExpression(ProcessJParser.PostfixExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CastExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCastExpression(ProcessJParser.CastExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EqualityExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualityExpression(ProcessJParser.EqualityExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MultiplicativeExpression}
	 * labeled alternative in {@link ProcessJParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeExpression(ProcessJParser.MultiplicativeExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#assignOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignOp(ProcessJParser.AssignOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimary(ProcessJParser.PrimaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#creator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreator(ProcessJParser.CreatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#creatorName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreatorName(ProcessJParser.CreatorNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#arrayExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayExpression(ProcessJParser.ArrayExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#recordExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecordExpression(ProcessJParser.RecordExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#recordExpressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecordExpressionList(ProcessJParser.RecordExpressionListContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#protocolExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProtocolExpression(ProcessJParser.ProtocolExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#protocolExpressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProtocolExpressionList(ProcessJParser.ProtocolExpressionListContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#tagExpressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTagExpressionList(ProcessJParser.TagExpressionListContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#classExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassExpression(ProcessJParser.ClassExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#typeArgumentsOrDiamond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeArgumentsOrDiamond(ProcessJParser.TypeArgumentsOrDiamondContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(ProcessJParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link ProcessJParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(ProcessJParser.IdentifierContext ctx);
}